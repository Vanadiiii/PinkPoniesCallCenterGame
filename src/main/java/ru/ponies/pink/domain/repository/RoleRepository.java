package ru.ponies.pink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ponies.pink.domain.entity.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
