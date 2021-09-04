package ru.ponies.pink.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.ponies.pink.domain.entity.Subject;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SubjectSerializer extends StdSerializer<List<Subject>> {
    public SubjectSerializer() {
        super(null, false);
    }

    protected SubjectSerializer(Class<List<Subject>> t) {
        super(t);
    }

    protected SubjectSerializer(JavaType type) {
        super(type);
    }

    protected SubjectSerializer(Class<?> t, boolean dummy) {
        super(t, dummy);
    }

    protected SubjectSerializer(StdSerializer<?> src) {
        super(src);
    }

    @Override
    public void serialize(List<Subject> subjects, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final List<UUID> uuids = subjects.stream().map(Subject::getId).collect(Collectors.toList());
        jsonGenerator.writeObject(uuids);
    }
}
