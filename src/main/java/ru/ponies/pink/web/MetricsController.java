package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ponies.pink.service.StrategyService;
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

    private final StrategyService strategyService;
    private final MetricsDtoMapper mapper;

    @PostMapping
    public ResponseEntity<Boolean> create(@NotNull @RequestBody MetricsDto metricsDto) {
        final val metrics = Optional.of(metricsDto)
                .map(mapper)
                .orElseThrow();

        return ResponseEntity.ok(strategyService.updateStatistic(metrics));
    }
}
