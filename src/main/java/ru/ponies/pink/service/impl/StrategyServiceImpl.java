package ru.ponies.pink.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.domain.repository.SubjectRepository;
import ru.ponies.pink.service.StrategyService;
import ru.ponies.pink.service.mapper.StrategyMapper;
import ru.ponies.pink.web.dto.StrategyDto;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StrategyServiceImpl implements StrategyService {

    private final StrategyRepository strategyRepository;
    private final SubjectRepository subjectRepository;

    private final StrategyMapper strategyMapper;

    @Override
    public Strategy getById(UUID strategyId) {
        return strategyRepository.findById(strategyId).orElse(null);
    }

    @Override
    public Strategy create(StrategyDto strategyDto) {
        Subject subject = subjectRepository.findById(strategyDto.getSubjectId()).orElseThrow();
        return strategyRepository.save(strategyMapper.map(strategyDto, subject));
    }

    @Override
    public Strategy update(StrategyDto strategyDto) {
        Subject subject = subjectRepository.findById(strategyDto.getSubjectId()).orElseThrow();
        Strategy newStrategy = strategyMapper.map(strategyDto, subject);
        Strategy oldStrategy = strategyRepository.findById(strategyDto.getId()).orElse(Strategy.builder().build());
        strategyMapper.map(oldStrategy, newStrategy);
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

}
