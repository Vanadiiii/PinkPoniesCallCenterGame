package ru.ponies.pink.service.mapper;

import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.web.dto.StrategyDto;

import java.util.List;
import java.util.UUID;

public interface StrategyService {
    Strategy getById(UUID strategyId);

    Strategy create(StrategyDto strategyDto);

    Strategy update(StrategyDto strategyDto);

    List<Strategy> findAll();

    void deleteById(UUID strategyId);
}
