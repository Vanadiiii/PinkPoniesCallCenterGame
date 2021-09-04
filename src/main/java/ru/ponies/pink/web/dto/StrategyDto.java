package ru.ponies.pink.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrategyDto {
    private UUID id;
    private UUID subjectId;
    private Map<String, String> reward;
    private List<ConditionDto> conditions;
}
