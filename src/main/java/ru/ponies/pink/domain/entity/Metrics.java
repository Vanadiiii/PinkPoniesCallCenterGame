package ru.ponies.pink.domain.entity;

import lombok.*;
import ru.ponies.pink.domain.enums.MetricsType;

import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {
    private UUID subjectId;
    private MetricsType type;
    private Map<String, String> metrics;
}
