package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.service.UserService;
import ru.ponies.pink.web.dto.UserDto;
import ru.ponies.pink.web.mapper.UserDtoMapper;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController extends CrudController<UUID, UserDto, User> {
    private final UserService userService;
    private final UserDtoMapper mapper;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<User> create(UserDto create) {
        return Optional.of(create)
                .map(mapper)
                .map(userService::save)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @Override
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<User> update(@NotNull @RequestBody UserDto update) {
        throw new RuntimeException("method not support");
    }

    @Override
    @PatchMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<User> patch(@NotNull @RequestBody UserDto update) {
        throw new RuntimeException("method not support");
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<User> get(UUID uuid) {
        throw new RuntimeException("method not support");
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<User> get(@PathVariable("uuid") UUID uuid, Principal principal) {
        return Optional.of(userService.findByLogin(uuid, principal.getName()))
                .map(ResponseEntity::ok)
                .orElseThrow();
    }


}