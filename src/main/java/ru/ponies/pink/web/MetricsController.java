package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ponies.pink.service.MetricsService;
import ru.ponies.pink.web.dto.MetricsDto;
import ru.ponies.pink.web.mapper.MetricsDtoMapper;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/metrics")
@PreAuthorize("isAuthenticated() && hasAnyRole('ROLE_BIGBROTHER')")
public class MetricsController {

    private final MetricsService metricsService;
    private final MetricsDtoMapper mapper;
    @PostMapping
    public ResponseEntity<Void> update(@NotNull @RequestBody MetricsDto metricsDto) {
        metricsService.update(Optional.of(metricsDto)
                .map(mapper)
                .orElseThrow());
        return ResponseEntity.ok().build();
    }
}
