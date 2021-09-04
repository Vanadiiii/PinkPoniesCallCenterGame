package ru.ponies.pink.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.web.dto.StrategyDto;

@Mapper(componentModel = "spring")
public interface StrategyDtoMapper {

    @Mapping(target = "reward", ignore = true)
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "conditions", ignore = true)
    @Mapping(target = "id", source = "dto.id")
    Strategy map(StrategyDto dto, Subject subject);
}
