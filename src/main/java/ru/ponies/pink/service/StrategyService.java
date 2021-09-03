package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.Strategy;

import java.util.UUID;

public interface StrategyService {

    Strategy get(UUID id);

    Strategy update(Strategy entity);

    void delete(UUID id);

    Strategy create(Strategy entity);

}
