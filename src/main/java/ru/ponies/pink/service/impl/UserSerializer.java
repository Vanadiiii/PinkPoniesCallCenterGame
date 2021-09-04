package ru.ponies.pink.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.ponies.pink.domain.entity.User;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserSerializer extends StdSerializer<List<User>> {
    protected UserSerializer(Class<List<User>> t) {
        super(t);
    }

    protected UserSerializer(JavaType type) {
        super(type);
    }

    protected UserSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    public UserSerializer() {
        super(null, false);
    }

    protected UserSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(List<User> subjects, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final List<UUID> uuids = subjects.stream().map(User::getId).collect(Collectors.toList());
        jsonGenerator.writeObject(uuids);
    }
}
