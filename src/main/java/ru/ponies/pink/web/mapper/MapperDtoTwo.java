package ru.ponies.pink.web.mapper;

import ru.ponies.pink.domain.entity.EntityTwo;
import ru.ponies.pink.web.dto.DtoTwo;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface MapperDtoTwo extends Function<DtoTwo, EntityTwo> {
}
