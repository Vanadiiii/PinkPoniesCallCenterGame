package ru.ponies.pink.web.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class StrategyDto {
    UUID id;
    UUID subjectId;
    Map<String, String> reward;
    List<ConditionDto> conditions;
}
