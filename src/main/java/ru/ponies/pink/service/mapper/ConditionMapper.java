package ru.ponies.pink.service.mapper;

import org.mapstruct.Mapper;
import ru.ponies.pink.domain.entity.Condition;
import ru.ponies.pink.web.dto.ConditionDto;

@Mapper(componentModel = "spring")
public interface ConditionMapper {

    Condition map(ConditionDto source);
}
