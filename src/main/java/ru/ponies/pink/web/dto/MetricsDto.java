package ru.ponies.pink.web.dto;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class MetricsDto {
    private UUID id;
    private String type;
    private Map<String, String> metrics;
}
