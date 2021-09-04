package ru.ponies.pink.service.impl;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ru.ponies.pink.domain.entity.Reward;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.domain.entity.enums.SubjectType;
import ru.ponies.pink.domain.repository.SubjectRepository;
import ru.ponies.pink.exception.EntityNotFoundException;
import ru.ponies.pink.service.RecommendationSystem;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.primitives.Doubles.max;
import static java.util.stream.Collectors.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecommendationSystemImpl implements RecommendationSystem {

    private final SubjectRepository subjectRepository;

    @AllArgsConstructor
    @Getter
    @Setter
    protected class StatisticsPair {
        private String key;
        private Double value;
    }

    @Getter
    @Setter
    protected class Importance {
        private Double strong;
        private Double average;
        private Double weak;
        private Double low;

        public Importance(Double value){
            this.strong = 0.0;
            this.average = 0.0;
            this.weak = 0.0;
            this.low = 0.0;

            if (value > 0.2){
                this.strong = 1.0;
            }
            else if(value > 0.15){
                this.strong = (value - 0.15) / 0.05;
            }

            if (value < 0.0){
                this.low = 1.0;
            }
            else if(value < 0.05){
                this.low = (0.05 - value) / 0.05;
            }

            this.average = calculatePhase(value, 0.2, 0.1);
            this.weak = calculatePhase(value, 0.1, 0.0);
        }

        private Double calculatePhase(Double value, Double a, Double b){
            Double phase = 0.0;
            Double middle = (a + b) / 2.0;
            Double radius = (a - b) / 2.0;
            if ((value < a) && (value > middle)){
                phase = (a - value) / radius;
            }
            else if(value > b){
                phase = (value - b) / radius;
            }
            return phase;
        }

    }

    @Override
    public Strategy recommend(UUID id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> EntityNotFoundException.forSubject.apply(id));
        Map<String, Double> typeAverageRewards = getAverageStatisticsBySubjectType(subject.getSubjectType());
        Map<String, Double> subjectRewards = getStatisticsBySubject(subject);

        StatisticsPair maxDeviation = findMaxDeviation(typeAverageRewards, subjectRewards);
        StatisticsPair minRewardDeviation = findMinDeviation(typeAverageRewards, subjectRewards);
        String rewardKey = null;
        if (maxDeviation.key != null) {
            Double value = fuzzyLogicModel(maxDeviation, minRewardDeviation);
            if (value < 2) {
                rewardKey = minRewardDeviation.key;
            }
            else {
                rewardKey = maxDeviation.key;
            }
        }
        else{
            rewardKey = minRewardDeviation.key;
        }



        return findStrategyWithMaxReward(subject, rewardKey);
    }

    private Strategy findStrategyWithMaxReward(Subject subject, String rewardKey){
        List<Strategy> strategies = subject.getStrategies().stream().filter(Strategy::getIsComplete).collect(toList());
        Strategy max = null;
        Integer maxValue = 0;
        for (Strategy strategy : strategies){
            List<Reward> rewards = strategy.getReward().stream().filter(reward -> rewardKey.equals(reward.getRewardType())).collect(toList());
            for (Reward reward : rewards){
                if (reward.getValue() > maxValue){
                    maxValue = reward.getValue();
                    max = strategy;
                }
            }
        }
        return max;
    }


    private StatisticsPair findMaxDeviation(Map<String, Double> typeAverageRewards, Map<String, Double> subjectRewards) {
        StatisticsPair maxDeviation = new StatisticsPair(null, 0.0);

        for (Map.Entry<String, Double> entry : typeAverageRewards.entrySet()){
            Double subjectValue = subjectRewards.getOrDefault(entry.getKey(), 0.0);
            if (entry.getValue() > 0.0) {
                Double temp =  (entry.getValue() - subjectValue) / entry.getValue();
                if (temp > maxDeviation.value){
                    maxDeviation.value = temp;
                    maxDeviation.key = entry.getKey();
                }
            }
        }

        return maxDeviation;
    }

    private StatisticsPair findMinDeviation(Map<String, Double> typeAverageRewards, Map<String, Double> subjectRewards) {
        Map.Entry<String, Double> entry = typeAverageRewards.entrySet().stream().min(Comparator.comparingDouble(Map.Entry::getValue)).orElseThrow(() -> new RuntimeException("No rewards"));
        Double averageValue = typeAverageRewards.get(entry.getKey());
        StatisticsPair minRewardDeviation = new StatisticsPair(entry.getKey(), 0.0);

        if (averageValue > 0.0){
            minRewardDeviation.value = (averageValue - entry.getValue()) / averageValue;
        }

        return minRewardDeviation;
    }

    private Map<String, Double> getAverageStatisticsBySubjectType(SubjectType subjectType) {
        List<Subject> subjects = subjectRepository.findBySubjectType(subjectType);
        return subjects.stream()
                .map(this::getStatisticsBySubject)
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getKey,
                                averagingDouble(Map.Entry::getValue)
                        )
                );
    }

    private Map<String, Double> getStatisticsBySubject(Subject subject) {
        return subject.getStrategies()
                .stream()
                .filter(Strategy::getIsComplete)
                .map(Strategy::getReward)
                .flatMap(Collection::stream)
                .collect(
                        Collectors.groupingBy(
                                Reward::getRewardType,
                                summingDouble(reward -> reward.getValue().doubleValue())
                        )
                );
    }

    private Double fuzzyLogicModel(StatisticsPair maxDeviation, StatisticsPair minimalRewardDeviation) {
        Importance maxImportance = new Importance(maxDeviation.value);
        Importance minImportance = new Importance(minimalRewardDeviation.value);
        Double low = max(
                maxImportance.low * minImportance.low,
                maxImportance.low * minImportance.weak,
                maxImportance.low * minImportance.average,
                maxImportance.weak * minImportance.low,
                maxImportance.weak * minImportance.weak);
        Double weak = max(
                maxImportance.low * minImportance.strong,
                maxImportance.weak * minImportance.average,
                maxImportance.weak * minImportance.strong,
                maxImportance.average * minImportance.low);
        Double average = max(
                maxImportance.strong * minImportance.low,
                maxImportance.average * minImportance.weak,
                maxImportance.strong * minImportance.weak);
        Double strong = max(
                maxImportance.average * minImportance.average,
                maxImportance.strong * minImportance.average,
                maxImportance.strong * minImportance.strong,
                maxImportance.average * minImportance.strong);

        return (low * 1 + weak * 2 + average * 3 + strong * 4) / (low + weak + average + strong);
    }
}
