package ru.ponies.pink.service.mapper.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.entity.Role;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.service.mapper.UserDetailMapper;

import java.util.stream.Collectors;

@Component
public class UserDetailMapperImpl implements UserDetailMapper {
    @Override
    public UserDetails apply(User user) {
        if (user == null) {
            return null;
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}
