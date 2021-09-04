package ru.ponies.pink.web.dto;

import lombok.Data;
import ru.ponies.pink.domain.entity.enums.SubjectType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Data
public class MetricsDto {
    private UUID subjectId;
    private SubjectType type;
    private Map<String, BigDecimal> metrics;
}
