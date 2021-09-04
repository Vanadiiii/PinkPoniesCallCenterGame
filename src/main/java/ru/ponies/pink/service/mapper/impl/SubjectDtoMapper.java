package ru.ponies.pink.service.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ponies.pink.domain.entity.Subject;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.service.mapper.ConditionMapper;
import ru.ponies.pink.web.dto.SubjectDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = ConditionMapper.class)
public interface SubjectDtoMapper {

    @Mapping(target = "subjectType", source = "dto.subjectType")
    @Mapping(target = "users", source = "users")
    @Mapping(target = "id", source = "dto.id")
    Subject map(SubjectDto dto, List<User> users);

}
