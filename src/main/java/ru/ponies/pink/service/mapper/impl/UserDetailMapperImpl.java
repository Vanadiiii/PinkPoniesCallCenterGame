package ru.ponies.pink.service.mapper.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.service.mapper.UserDetailMapper;

import java.util.Collections;

@Component
public class UserDetailMapperImpl implements UserDetailMapper {
    @Override
    public org.springframework.security.core.userdetails.User apply(User user) {
        if (user == null) {
            return null;
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(user.getRole().name())
                )
        );
    }
}
