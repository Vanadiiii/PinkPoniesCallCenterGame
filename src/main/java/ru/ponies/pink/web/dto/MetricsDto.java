package ru.ponies.pink.web.dto;

import lombok.Data;
import org.springframework.jmx.support.MetricType;

import java.util.Map;
import java.util.UUID;

@Data
public class MetricsDto {
    private UUID subjectId;
    private MetricType type;
    private Map<String, String> metrics;
}
