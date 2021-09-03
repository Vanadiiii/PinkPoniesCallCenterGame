package ru.ponies.pink.domain.repository;

import ru.ponies.pink.domain.entity.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StrategyRepository extends JpaRepository<Strategy, UUID> {
}
