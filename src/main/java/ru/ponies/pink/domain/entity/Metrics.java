package ru.ponies.pink.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.ponies.pink.domain.entity.enums.SubjectType;

import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metrics {
    private UUID subjectId;
    private SubjectType type;
    private Map<String, String> metrics;
}
