package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.security.enums.RoleType;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User save(User user);

    void addToUser(String login, RoleType role);

    User findByLogin(UUID id, String login);

    List<User> getAll();
}
