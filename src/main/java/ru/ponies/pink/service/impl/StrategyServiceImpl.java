package ru.ponies.pink.service.impl;

import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.repository.EntityOneRepository;
import ru.ponies.pink.domain.repository.EntityThreeRepository;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.service.ServiceThree;
import ru.ponies.pink.service.StrategyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StrategyServiceImpl implements StrategyService {

    private final StrategyRepository strategyRepository;

    @Override
    public Strategy get(UUID id) {
        return strategyRepository.findById(id).orElse(null);
    }

    @Override
    public Strategy update(Strategy entity) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        strategyRepository.deleteById(id);
    }

    @Override
    public Strategy create(Strategy entity) {
        return null;
    }
}
