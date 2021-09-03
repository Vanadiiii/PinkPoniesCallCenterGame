package ru.ponies.pink.domain.repository;

import ru.ponies.pink.domain.entity.EntityOne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntityOneRepository extends JpaRepository<EntityOne, UUID> {
}
