package ru.ponies.pink.web.dto;

import lombok.Builder;
import lombok.Value;
import ru.ponies.pink.domain.entity.enums.CompareMethod;

@Value
@Builder
public class ConditionDto {
    String metricName;
    CompareMethod method;
    String value;
}
