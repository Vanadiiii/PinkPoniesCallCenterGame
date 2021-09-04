package ru.ponies.pink.web.mapper;

import org.mapstruct.Mapper;
import ru.ponies.pink.domain.entity.User;
import ru.ponies.pink.web.dto.UserDto;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface UserDtoMapper extends Function<UserDto, User> {
}
