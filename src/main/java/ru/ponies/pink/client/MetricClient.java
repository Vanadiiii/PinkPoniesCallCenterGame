package ru.ponies.pink.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ponies.pink.client.dto.MetricDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "metric-mock-client", url = "localhost:8081")
@RequestMapping("/mock/metrics")
public interface MetricClient {

    @GetMapping("/{metricId}")
    ResponseEntity<MetricDto> getMetricById(@PathVariable("metricId") UUID metricId);

    @GetMapping
    ResponseEntity<List<MetricDto>> getAllMetrics();

    @PostMapping
    ResponseEntity<UUID> addMetric(@RequestBody MetricDto metric);

    @PutMapping
    ResponseEntity<MetricDto> updateMetric(@RequestBody MetricDto metric);
}
