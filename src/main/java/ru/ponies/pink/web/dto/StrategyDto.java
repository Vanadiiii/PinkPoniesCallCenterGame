package ru.ponies.pink.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class StrategyDto {
    private UUID id;
    private UUID subjectId;
    private Map<String, String> reward;
    private List<ConditionDto> conditions;
}
