package ru.ponies.pink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ponies.pink.domain.entity.Reward;

import java.util.UUID;

public interface RewardRepository extends JpaRepository<Reward, UUID> {
}
