package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.service.StrategyService;
import ru.ponies.pink.web.dto.StrategyDto;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/strategy")
@PreAuthorize("isAuthenticated()")
public class StrategyController extends CrudController<UUID, StrategyDto, Strategy> {

    private final StrategyService strategyService;

    @GetMapping
    public ResponseEntity<List<Strategy>> findAll() {
        return ResponseEntity.ok(strategyService.findAll());
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Strategy> create(@RequestBody StrategyDto create) {
        return ResponseEntity.ok(strategyService.create(create));
    }

    @Override
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Strategy> update(@RequestBody StrategyDto update) {
        return ResponseEntity.ok(strategyService.update(update));
    }

    @Override
    public ResponseEntity<Strategy> patch(@RequestBody StrategyDto update) {
        return null;
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<Strategy> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(strategyService.getById(uuid));
    }

    @Override
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") UUID uuid) {
        strategyService.deleteById(uuid);
        return ResponseEntity.ok().build();
    }

}