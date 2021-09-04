package ru.ponies.pink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ponies.pink.domain.entity.Condition;

import java.util.UUID;

public interface ConditionRepository extends JpaRepository<Condition, UUID> {
}
