package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User save(User user);

    User findByLogin(UUID id, String login);

    User findById(UUID id);

    List<User> getAll();
}
