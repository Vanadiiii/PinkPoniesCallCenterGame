package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ponies.pink.domain.entity.Strategy;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.service.UserService;
import ru.ponies.pink.web.dto.StrategyDto;
import ru.ponies.pink.web.dto.UserDto;
import ru.ponies.pink.web.mapper.UserDtoMapper;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/strategy")
@PreAuthorize("isAuthenticated()")
public class StrategyController extends CrudController<UUID, StrategyDto, Strategy> {

    @GetMapping
    public ResponseEntity<List<Strategy>> findAll() {
        return ResponseEntity.ok(List.of(new Strategy()));
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Strategy> create(StrategyDto create) {
        return null;
    }

    @Override
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Strategy> update(StrategyDto update) {
        return null;
    }

    @Override
    @PatchMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Strategy> patch(StrategyDto update) {
        return null;
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<Strategy> get(@PathVariable("uuid") UUID uuid) {
        return null;
    }

}