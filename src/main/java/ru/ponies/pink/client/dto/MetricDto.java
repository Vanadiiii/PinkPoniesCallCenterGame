package ru.ponies.pink.client.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class MetricDto {
    UUID id;
    String type;
    Map<String, Double> metrics;
}
