package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated() && hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
public class UserController {
    private final UserService userService;
    private final UserDtoMapper mapper;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public ResponseEntity<User> save(@NotNull @RequestBody UserDto userDto) {
        return Optional.of(userDto)
                .map(mapper)
                .map(userService::save)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PutMapping
    public ResponseEntity<Void> addToUser(@PathParam("roleName") String roleName,
                                          @PathParam("login") String login) {
        userService.addToUser(login, roleName);
        return ResponseEntity.ok().build();
    }
}