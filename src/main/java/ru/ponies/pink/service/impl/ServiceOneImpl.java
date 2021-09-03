package ru.ponies.pink.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.entity.EntityOne;
import ru.ponies.pink.domain.repository.EntityOneRepository;
import ru.ponies.pink.domain.repository.EntityThreeRepository;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.service.ServiceOne;
import ru.ponies.pink.service.StrategyService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ServiceOneImpl implements ServiceOne {

    private final StrategyService strategyService;

    private final EntityOneRepository entityOneRepository;
    private final StrategyRepository strategyRepository;
    private final EntityThreeRepository entityThreeRepository;

    @Override
    public EntityOne get(UUID id) {
        return null;
    }

    @Override
    public EntityOne update(EntityOne entity) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public EntityOne create(EntityOne entity) {
        return null;
    }
}
