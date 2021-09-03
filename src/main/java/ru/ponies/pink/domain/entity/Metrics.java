package ru.ponies.pink.domain.entity;

import lombok.*;

import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {
    private UUID id;
    private String type;
    private Map<String, String> metrics;
}
