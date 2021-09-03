package ru.ponies.pink.service.impl;

import ru.ponies.pink.service.ProcessManager;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessManagerImpl implements ProcessManager {

    private final RuntimeService runtimeService;

    @Override
    public void startProcess() {
        runtimeService.createProcessInstanceById("")
                .businessKey("")
                .execute();
    }
}
