package ru.ponies.pink.web.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.ponies.pink.domain.entity.enums.SubjectType;

import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class SubjectDto {
    private UUID id;
    private List<UUID> usersId;
    private SubjectType subjectType;
}
