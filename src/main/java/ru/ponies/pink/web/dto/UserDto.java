package ru.ponies.pink.web.dto;

import lombok.Data;
import ru.ponies.pink.security.enums.RoleType;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String login;
    private String password;
    private RoleType roles;
}
