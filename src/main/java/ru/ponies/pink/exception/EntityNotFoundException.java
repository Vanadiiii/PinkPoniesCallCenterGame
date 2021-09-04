package ru.ponies.pink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.BiFunction;
import java.util.function.Function;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public static Function<Object, EntityNotFoundException> forStrategy = id -> new EntityNotFoundException("Не возможно найти стратегию с идентификатором " + id);
    public static Function< Object, EntityNotFoundException> forSubject = id -> new EntityNotFoundException("Не возможно найти структурную единицу с идентификатором " + id);

}
