package ru.ponies.pink.domain.delegate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.repository.EntityOneRepository;
import ru.ponies.pink.domain.repository.EntityThreeRepository;
import ru.ponies.pink.domain.repository.StrategyRepository;
import ru.ponies.pink.service.ServiceOne;
import ru.ponies.pink.service.ServiceThree;
import ru.ponies.pink.service.StrategyService;

@Component
@RequiredArgsConstructor
public class DelegateTwo {

    private final ServiceOne serviceOne;
    private final StrategyService strategyService;
    private final ServiceThree serviceThree;

    private final EntityOneRepository entityOneRepository;
    private final StrategyRepository strategyRepository;
    private final EntityThreeRepository entityThreeRepository;

}
