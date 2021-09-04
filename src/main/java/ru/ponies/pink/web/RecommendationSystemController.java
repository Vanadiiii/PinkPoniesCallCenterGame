package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.service.RecommendationSystem;
import ru.ponies.pink.service.mapper.StrategyDtoMapper;
import ru.ponies.pink.web.dto.StrategyDto;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recommendation")
@PreAuthorize("isAuthenticated()")
public class RecommendationSystemController {

    final private RecommendationSystem recommendationSystem;

    @GetMapping("/{subjectId}")
    public ResponseEntity<Strategy> recommend(@PathVariable UUID subjectId) {

        return ResponseEntity.ok(recommendationSystem.recommend(subjectId));
    }
}
