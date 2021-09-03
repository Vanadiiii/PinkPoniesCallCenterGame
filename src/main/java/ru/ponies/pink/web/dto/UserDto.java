package ru.ponies.pink.web.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String name;
    private String login;
    private String password;
    private List<RoleDto> roles;
}
