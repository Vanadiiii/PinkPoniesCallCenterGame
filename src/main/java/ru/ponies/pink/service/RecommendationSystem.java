package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.Strategy;

import java.util.UUID;

public interface RecommendationSystem {

    Strategy recommend(UUID id);

}
