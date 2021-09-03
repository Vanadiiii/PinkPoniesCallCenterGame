package ru.ponies.pink.domain.delegate;

import ru.ponies.pink.domain.repository.EntityOneRepository;
import ru.ponies.pink.domain.repository.EntityThreeRepository;
import ru.ponies.pink.domain.repository.EntityTwoRepository;
import ru.ponies.pink.service.ServiceOne;
import ru.ponies.pink.service.ServiceThree;
import ru.ponies.pink.service.ServiceTwo;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DelegateOne implements JavaDelegate {

    private final ServiceOne serviceOne;
    private final ServiceTwo serviceTwo;
    private final ServiceThree serviceThree;

    private final EntityOneRepository entityOneRepository;
    private final EntityTwoRepository entityTwoRepository;
    private final EntityThreeRepository entityThreeRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
