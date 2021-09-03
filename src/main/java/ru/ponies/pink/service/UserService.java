package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.Role;
import ru.ponies.pink.domain.entity.User;

import java.util.List;

public interface UserService {
    User save(User user);

    Role save(Role role);

    void addToUser(String login, String roleName);

    User findByLogin(String login);

    List<User> getAll();
}
