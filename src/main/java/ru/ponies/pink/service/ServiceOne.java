package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.EntityOne;

import java.util.UUID;

public interface ServiceOne {

    EntityOne get(UUID id);

    EntityOne update(EntityOne entity);

    void delete(UUID id);

    EntityOne create(EntityOne entityOne);

}
