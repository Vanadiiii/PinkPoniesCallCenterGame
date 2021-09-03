package ru.ponies.pink.service.impl;

import ru.ponies.pink.domain.entity.EntityThree;
import ru.ponies.pink.domain.repository.EntityOneRepository;
import ru.ponies.pink.domain.repository.EntityThreeRepository;
import ru.ponies.pink.domain.repository.EntityTwoRepository;
import ru.ponies.pink.service.ServiceOne;
import ru.ponies.pink.service.ServiceThree;
import ru.ponies.pink.service.ServiceTwo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ServiceThreeImpl implements ServiceThree {

    private final ServiceOne serviceOne;
    private final ServiceTwo serviceTwo;

    private final EntityOneRepository entityOneRepository;
    private final EntityTwoRepository entityTwoRepository;
    private final EntityThreeRepository entityThreeRepository;

    @Override
    public EntityThree get(UUID id) {
        return null;
    }

    @Override
    public EntityThree update(EntityThree entity) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public EntityThree create(EntityThree entity) {
        return null;
    }
}
