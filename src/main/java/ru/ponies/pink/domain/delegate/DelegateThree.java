package ru.ponies.pink.domain.delegate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.repository.EntityOneRepository;
import ru.ponies.pink.domain.repository.EntityThreeRepository;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.service.ServiceOne;
import ru.ponies.pink.service.ServiceThree;
import ru.ponies.pink.service.ServiceTwo;

@Component
@RequiredArgsConstructor
public class DelegateThree {

    private final ServiceOne serviceOne;
    private final ServiceTwo serviceTwo;
    private final ServiceThree serviceThree;

    private final EntityOneRepository entityOneRepository;
    private final StrategyRepository strategyRepository;
    private final EntityThreeRepository entityThreeRepository;
}
