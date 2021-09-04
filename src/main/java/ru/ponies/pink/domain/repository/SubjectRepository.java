package ru.ponies.pink.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.domain.entity.enums.SubjectType;

import java.util.List;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    List<Subject> findBySubjectType(SubjectType subjectType);
}
