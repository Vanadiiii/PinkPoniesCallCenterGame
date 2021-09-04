package ru.ponies.pink.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.ponies.pink.domain.entity.Strategy;

@Mapper(componentModel = "spring")
public interface StrategyPatcher {

    void patch(@MappingTarget Strategy target, Strategy source);

}
