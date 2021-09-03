package ru.ponies.pink.service.mapper;

import org.mapstruct.Mapper;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.web.dto.StrategyDto;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface StrategyMapper extends Function<StrategyDto, Strategy> {

}
