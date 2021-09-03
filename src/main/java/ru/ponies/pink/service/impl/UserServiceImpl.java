package ru.ponies.pink.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ponies.pink.domain.entity.Role;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.domain.repository.RoleRepository;
import ru.ponies.pink.domain.repository.UserRepository;
import ru.ponies.pink.exception.RoleNotFoundException;
import ru.ponies.pink.exception.UserNotFoundException;
import ru.ponies.pink.service.UserService;
import ru.ponies.pink.service.mapper.UserDetailMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailMapper userDetailMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByLogin(email)
                .map(userDetailMapper)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with email - " + email));
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("saving user {}", user);
        return userRepository.save(user);
    }

    @Override
    public Role save(Role role) {
        log.info("saving role {}", role);
        return roleRepository.save(role);
    }

    @Override
    public void addToUser(String login, String roleName) {
        log.info("adding role - {} to user with login - {}", roleName, login);
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> UserNotFoundException.ofLogin(login));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> RoleNotFoundException.ofName(roleName));

        user.getRoles().add(role);
    }

    @Override
    public User findByLogin(String login) {
        log.info("fetching user with login - {}", login);
        return userRepository.findByLogin(login)
                .orElseThrow(() -> UserNotFoundException.ofLogin(login));
    }

    @Override
    public List<User> getAll() {
        log.info("fetching all users");
        return userRepository.findAll();
    }
}
