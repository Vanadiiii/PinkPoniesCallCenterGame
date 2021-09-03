package ru.ponies.pink.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.ponies.pink.domain.entity.Reward;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.web.dto.StrategyDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ConditionMapper.class)
public interface StrategyMapper {

    @Mapping(target = "reward", source = "strategyDto.reward", qualifiedByName = "mapReward")
    @Mapping(target = "subject", source = "subject")
    @Mapping(target = "conditions", source = "strategyDto.conditions")
    @Mapping(target = "id", source = "strategyDto.id")
    Strategy map(StrategyDto strategyDto, Subject subject);

    @Named("mapReward")
    default List<Reward> mapReward(Map<String, String> value) {
        return value
                .entrySet()
                .stream()
                .map(it -> new Reward(it.getKey(), Integer.valueOf(it.getValue())))
                .collect(Collectors.toList());
    }

    void map(@MappingTarget Strategy target, Strategy source);

}
