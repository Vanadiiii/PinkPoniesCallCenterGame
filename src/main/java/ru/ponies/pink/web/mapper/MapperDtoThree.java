package ru.ponies.pink.web.mapper;

import ru.ponies.pink.domain.entity.EntityThree;
import ru.ponies.pink.web.dto.DtoThree;
import org.mapstruct.Mapper;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface MapperDtoThree extends Function<DtoThree, EntityThree> {
}
