package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.EntityTwo;

import java.util.UUID;

public interface ServiceTwo {

    EntityTwo get(UUID id);

    EntityTwo update(EntityTwo entity);

    void delete(UUID id);

    EntityTwo create(EntityTwo entity);

}
