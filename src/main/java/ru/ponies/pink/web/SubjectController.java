package ru.ponies.pink.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.service.SubjectService;
import ru.ponies.pink.web.dto.SubjectDto;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subject")
@PreAuthorize("isAuthenticated()")
public class SubjectController extends CrudController<UUID, SubjectDto, Subject> {

    private final SubjectService subjectService;

    @Override
    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Subject> get(@PathVariable UUID uuid) {
        return ResponseEntity.ok(subjectService.get(uuid));
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Subject> create(SubjectDto create) {
        return ResponseEntity.ok(subjectService.create(create));
    }

    @Override
    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Subject> update(SubjectDto update) {
        return ResponseEntity.ok(subjectService.update(update));
    }

    @Override
    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        subjectService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Subject> patch(SubjectDto update) {
        return null;
    }

    @Override
    public ResponseEntity<List<Subject>> findAll() {
        return null;
    }
}
