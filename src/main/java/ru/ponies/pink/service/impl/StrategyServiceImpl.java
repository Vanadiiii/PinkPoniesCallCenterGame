package ru.ponies.pink.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.entity.Condition;
import ru.ponies.pink.domain.entity.Metrics;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.domain.repository.SubjectRepository;
import ru.ponies.pink.service.StrategyService;
import ru.ponies.pink.service.mapper.StrategyDtoMapper;
import ru.ponies.pink.service.mapper.StrategyPatcher;
import ru.ponies.pink.web.dto.StrategyDto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StrategyServiceImpl implements StrategyService {

    private final StrategyRepository strategyRepository;
    private final SubjectRepository subjectRepository;

    private final StrategyDtoMapper strategyDtoMapper;
    private final StrategyPatcher strategyPatcher;

    @Override
    public Strategy getById(UUID strategyId) {
        return strategyRepository.findById(strategyId).orElse(null);
    }

    @Override
    public Strategy create(StrategyDto strategyDto) {
        Subject subject = subjectRepository.findById(strategyDto.getSubjectId()).orElseThrow();
        return strategyRepository.save(strategyDtoMapper.map(strategyDto, subject));
    }

    @Override
    public Strategy update(StrategyDto strategyDto) {
        Subject subject = subjectRepository.findById(strategyDto.getSubjectId()).orElseThrow();
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
        //to get completed ids
        final List<UUID> conditionComplete = subject.getStrategies().stream()
                .filter(it->it.getIsComplete()==null || !it.getIsComplete())
                .map(Strategy::getConditions)
                .flatMap(Collection::stream)
                .filter(it -> mat2metric.containsKey(it.getMetricName()))
                .filter(it -> compareToValue(mat2metric.get(it.getMetricName())).test(it))
                .map(Condition::getId)
                .collect(Collectors.toList());

        // try to remove
        for (Strategy st : subject.getStrategies()) {
            final var conditions = st.getConditions().stream().filter(it -> !conditionComplete.contains(it.getId())).collect(Collectors.toList());
            if (conditions.size() == 0) {
                applyToSubject(st);
            }
            st.setConditions(conditions);
            strategyRepository.save(st);
        }
        return true;
    }

    private void applyToSubject(Strategy st) {
        // TODO calculate metrics
        st.setIsComplete(true);
    }

    private Predicate<Condition> compareToValue(BigDecimal value) {
        return condition -> value != null && value.compareTo(condition.getValue()) >= 0;
    }

}
