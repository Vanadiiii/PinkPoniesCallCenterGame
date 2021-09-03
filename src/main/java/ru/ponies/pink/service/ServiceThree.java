package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.EntityThree;

import java.util.UUID;

public interface ServiceThree {

    EntityThree get(UUID id);

    EntityThree update(EntityThree entity);

    void delete(UUID id);

    EntityThree create(EntityThree entity);

}
