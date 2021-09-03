package ru.ponies.pink.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    public static RoleNotFoundException ofName(String roleName) {
        return new RoleNotFoundException("Can't find role with name - " + roleName);
    }
}
