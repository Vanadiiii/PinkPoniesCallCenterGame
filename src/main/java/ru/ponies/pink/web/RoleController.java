package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ponies.pink.domain.entity.Role;
import ru.ponies.pink.service.UserService;
import ru.ponies.pink.web.dto.RoleDto;
import ru.ponies.pink.web.mapper.RoleDtoMapper;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
public class RoleController {
    private final UserService userService;
    private final RoleDtoMapper mapper;

    @PostMapping
    public ResponseEntity<Role> save(@NotNull @RequestBody RoleDto roleDto) {
        return Optional.of(roleDto)
                .map(mapper)
                .map(userService::save)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }
}