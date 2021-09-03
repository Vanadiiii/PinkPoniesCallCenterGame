package ru.ponies.pink.web.dto;

import lombok.Builder;
import lombok.Value;
import ru.ponies.pink.domain.entity.enums.CompareMethod;

import java.util.UUID;

@Value
@Builder
public class ConditionDto {
    UUID id;
    String metricName;
    CompareMethod method;
    String value;
}
