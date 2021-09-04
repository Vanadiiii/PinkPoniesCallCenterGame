package ru.ponies.pink.service;

import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.web.dto.SubjectDto;

import java.util.UUID;

public interface SubjectService {
    Subject get(UUID uuid);

    Subject create(SubjectDto subjectDto);

    Subject update(SubjectDto subjectDto);

    void delete(UUID uuid);
}
