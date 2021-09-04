package ru.ponies.pink.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ponies.pink.security.enums.RoleType;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String login;
    private String password;
    private RoleType roles;
}
