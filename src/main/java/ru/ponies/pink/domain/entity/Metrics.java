package ru.ponies.pink.domain.entity;

import lombok.*;
import org.springframework.jmx.support.MetricType;

import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {
    private UUID subjectId;
    private MetricType type;
    private Map<String, String> metrics;
}
