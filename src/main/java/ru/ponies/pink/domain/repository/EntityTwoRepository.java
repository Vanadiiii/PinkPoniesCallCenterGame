package ru.ponies.pink.domain.repository;

import ru.ponies.pink.domain.entity.EntityTwo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntityTwoRepository extends JpaRepository<EntityTwo, UUID> {
}
