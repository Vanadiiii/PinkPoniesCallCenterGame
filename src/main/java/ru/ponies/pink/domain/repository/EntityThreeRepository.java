package ru.ponies.pink.domain.repository;

import ru.ponies.pink.domain.entity.EntityThree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntityThreeRepository extends JpaRepository<EntityThree, UUID> {
}
