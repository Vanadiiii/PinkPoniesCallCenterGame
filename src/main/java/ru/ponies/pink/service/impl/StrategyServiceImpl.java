package ru.ponies.pink.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.entity.Condition;
import ru.ponies.pink.domain.entity.Metrics;
import ru.ponies.pink.domain.entity.Reward;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.domain.entity.enums.CompareMethod;
import ru.ponies.pink.domain.repository.ConditionRepository;
import ru.ponies.pink.domain.repository.RewardRepository;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.domain.repository.SubjectRepository;
import ru.ponies.pink.exception.EntityNotFoundException;
import ru.ponies.pink.service.StrategyService;
import ru.ponies.pink.service.mapper.ConditionMapper;
import ru.ponies.pink.service.mapper.StrategyDtoMapper;
import ru.ponies.pink.service.mapper.StrategyPatcher;
import ru.ponies.pink.web.dto.StrategyDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StrategyServiceImpl implements StrategyService {

    private final StrategyRepository strategyRepository;
    private final SubjectRepository subjectRepository;
    private final RewardRepository rewardRepository;
    private final ConditionRepository conditionRepository;

    private final StrategyDtoMapper strategyDtoMapper;
    private final StrategyPatcher strategyPatcher;
    private final ConditionMapper conditionMapper;


    @Override
    public Strategy getById(UUID strategyId) {
        return strategyRepository.findById(strategyId).orElse(null);
    }

    @Override
    public Strategy create(StrategyDto strategyDto) {
        Subject subject = subjectRepository.findById(strategyDto.getSubjectId()).orElseThrow(() -> EntityNotFoundException.forSubject.apply(strategyDto.getSubjectId()));
        final List<Reward> reward = strategyDto.getReward().entrySet().stream().map(it -> new Reward(it.getKey(), it.getValue())).collect(Collectors.toList());
        final List<Condition> condition = strategyDto.getConditions().stream().map(conditionMapper::map).collect(Collectors.toList());
        var strategy = strategyRepository.save(strategyDtoMapper.map(strategyDto, subject));
        final List<Reward> rewards = reward.stream().peek(it -> it.setStrategy(strategy.getId())).map(rewardRepository::save).collect(Collectors.toList());
        final List<Condition> conditions = condition.stream().peek(it -> it.setStrategy(strategy.getId())).map(conditionRepository::save).collect(Collectors.toList());
        strategy.setReward(rewards);
        strategy.setConditions(conditions);
        return strategy;
    }

    @Override
    public Strategy update(StrategyDto strategyDto) {
        Subject subject = subjectRepository.findById(strategyDto.getSubjectId()).orElseThrow(() -> EntityNotFoundException.forSubject.apply(strategyDto.getSubjectId()));
        Strategy newStrategy = strategyDtoMapper.map(strategyDto, subject);
        Strategy oldStrategy = strategyRepository.findById(strategyDto.getId()).orElse(Strategy.builder().build());
        strategyPatcher.patch(oldStrategy, newStrategy);
        return strategyRepository.save(oldStrategy);
    }

    @Override
    public List<Strategy> findAll() {
        return strategyRepository.findAll();
    }

    @Override
    public void deleteById(UUID strategyId) {
        strategyRepository.deleteById(strategyId);
    }

    @Override
    public boolean updateStatistic(Metrics metrics) {
        Subject subject = subjectRepository.findById(metrics.getSubjectId())
                .orElseThrow();
        final var mat2metric = metrics.getMetrics();
        //to get Conditions completed ids
        final List<UUID> conditionComplete = subject.getStrategies().stream()
                .filter(it -> it.getIsComplete() == null || !it.getIsComplete())
                .map(Strategy::getConditions)
                .flatMap(Collection::stream)
                .filter(it -> mat2metric.containsKey(it.getMetricName()))
                .filter(it -> compareToValue.test(it, mat2metric.get(it.getMetricName())))
                .map(Condition::getId)
                .collect(Collectors.toList());

        // try to remove
        for (Strategy st : subject.getStrategies()) {
            final var conditions = st.getConditions().stream().filter(it -> !conditionComplete.contains(it.getId())).collect(Collectors.toList());
            if (conditions.size() == 0) {
                //TODO recalculate subject if necessary
                st.setIsComplete(true);
            }
            st.setConditions(conditions);
            strategyRepository.save(st);
        }
        return true;
    }


    private final BiPredicate<Condition, BigDecimal> compareToValue = (condition, value) -> {
        if (value == null) {
            return false;
        }
        if (CompareMethod.LESS.equals(condition.getMethod())) {
            return value.compareTo(condition.getValue()) < 0;
        }
        if (CompareMethod.MORE.equals(condition.getMethod())) {
            return value.compareTo(condition.getValue()) >= 0;
        }
        return false;
    };


}
