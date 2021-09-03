package ru.ponies.pink.web.mapper;

import ru.ponies.pink.domain.entity.EntityOne;
import ru.ponies.pink.web.dto.DtoOne;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface MapperDtoOne extends Function<DtoOne, EntityOne> {
}
