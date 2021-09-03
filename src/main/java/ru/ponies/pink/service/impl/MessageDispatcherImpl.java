package ru.ponies.pink.service.impl;

import ru.ponies.pink.service.MessageDispatcher;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageDispatcherImpl implements MessageDispatcher {

    private final RuntimeService runtimeService;

    @Override
    public void correlate(String message) {
        runtimeService.correlateMessage(message);
    }
}
