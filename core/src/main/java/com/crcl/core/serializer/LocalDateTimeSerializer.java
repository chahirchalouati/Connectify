package com.crcl.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The LocalDateTimeSerializer class is a custom serializer for LocalDateTime objects.
 * It extends the JsonSerializer class and is used by the ObjectMapper to serialize LocalDateTime objects to JSON strings.
 *
 * Usage:
 * 1. Create an instance of the ObjectMapper class.
 * 2. Register an instance of the LocalDateTimeSerializer class with the ObjectMapper.
 * 3. Use the ObjectMapper to serialize LocalDateTime objects to JSON strings.
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Serializes a LocalDateTime object to a JSON string.
     *
     * @param value The LocalDateTime object to be serialized.
     * @param gen The JsonGenerator object used to write the JSON string.
     * @param serializers The SerializerProvider object.
     * @throws IOException If an I/O error occurs during the serialization process.
     */
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(FORMATTER.format(value));
    }
}
