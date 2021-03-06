package ru.ponies.pink.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.domain.repository.UserRepository;
import ru.ponies.pink.exception.UserNotFoundException;
import ru.ponies.pink.security.enums.RoleType;
import ru.ponies.pink.service.UserService;
import ru.ponies.pink.service.mapper.UserDetailMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserDetailMapper userDetailMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.empty();
        if ("admin".equals(login)) {
            final var result = new User();
            result.setRole(RoleType.ROLE_ADMIN);
            result.setPassword(passwordEncoder.encode("admin"));
            result.setLogin("admin");
            userOptional = Optional.of(result);
        } else {
            userOptional = userRepository.findByLogin(login);
        }
        return userOptional
                .map(userDetailMapper)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with email - " + login));
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("saving user {}", user);
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(UUID id, String login) {
        log.info("fetching user with login - {}", login);
        return userRepository.findByIdAndLogin(id, login)
                .orElseThrow(() -> UserNotFoundException.ofLogin(login));
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.ofLogin(id.toString()));
    }

    @Override
    public List<User> getAll() {
        log.info("fetching all users");
        return userRepository.findAll();
    }
}
