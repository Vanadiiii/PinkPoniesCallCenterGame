package ru.ponies.pink.service.mapper.impl;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.ponies.pink.domain.entity.Subject;

@Mapper(componentModel = "spring")
public interface SubjectPatcher {

    void patch(@MappingTarget Subject target, Subject source);

}
