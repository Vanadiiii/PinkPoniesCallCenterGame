package ru.ponies.pink.service.impl;

import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.repository.EntityOneRepository;
import ru.ponies.pink.domain.repository.EntityThreeRepository;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.service.ServiceThree;
import ru.ponies.pink.service.ServiceTwo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ServiceTwoImpl implements ServiceTwo {

    private final ServiceThree serviceThree;

    private final EntityOneRepository entityOneRepository;
    private final StrategyRepository strategyRepository;
    private final EntityThreeRepository entityThreeRepository;

    @Override
    public Strategy get(UUID id) {
        return null;
    }

    @Override
    public Strategy update(Strategy entity) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Strategy create(Strategy entity) {
        return null;
    }
}
