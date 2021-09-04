package ru.ponies.pink.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.domain.repository.SubjectRepository;
import ru.ponies.pink.service.SubjectService;
import ru.ponies.pink.service.UserService;
import ru.ponies.pink.service.mapper.impl.SubjectDtoMapper;
import ru.ponies.pink.service.mapper.impl.SubjectPatcher;
import ru.ponies.pink.web.dto.SubjectDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectDtoMapper subjectDtoMapper;
    private final SubjectPatcher subjectPatcher;
    private final UserService userService;

    @Override
    public Subject get(UUID uuid) {
        return subjectRepository.findById(uuid).orElse(null);
    }

    @Override
    public Subject create(SubjectDto subjectDto) {
        List<User> users = subjectDto.getUsersId()
                .stream()
                .map(userService::findById)
                .collect(Collectors.toList());
        return subjectRepository.save(subjectDtoMapper.map(subjectDto, users));
    }

    @Override
    public Subject update(SubjectDto subjectDto) {
        List<User> users = subjectDto.getUsersId()
                .stream()
                .map(userService::findById)
                .collect(Collectors.toList());
        var newSubject = subjectDtoMapper.map(subjectDto, users);
        var oldSubject = subjectRepository.findById(subjectDto.getId()).orElse(new Subject());
        subjectPatcher.patch(oldSubject, newSubject);
        return subjectRepository.save(oldSubject);
    }

    @Override
    public void delete(UUID uuid) {
        subjectRepository.deleteById(uuid);
    }
}
