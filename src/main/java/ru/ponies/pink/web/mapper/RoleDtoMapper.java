package ru.ponies.pink.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.ponies.pink.domain.entity.Role;
import ru.ponies.pink.web.dto.RoleDto;

import java.util.function.Function;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RoleDtoMapper extends Function<RoleDto, Role> {
}
