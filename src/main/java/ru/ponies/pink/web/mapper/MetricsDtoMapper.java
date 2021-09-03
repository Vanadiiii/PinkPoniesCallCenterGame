package ru.ponies.pink.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.ponies.pink.domain.entity.Metrics;
import ru.ponies.pink.web.dto.MetricsDto;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface MetricsDtoMapper  extends Function<MetricsDto, Metrics> {

}
