package ru.ponies.pink.service.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import ru.ponies.pink.domain.entity.User;

import java.util.function.Function;

public interface UserDetailMapper extends Function<User, UserDetails> {
}
