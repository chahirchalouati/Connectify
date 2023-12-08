package com.crcl.core.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The LocalDateTimeDeserializer class is a custom deserializer for LocalDateTime objects.
 * It extends the JsonDeserializer class and is used by the ObjectMapper to deserialize JSON strings to LocalDateTime objects.
 *
 * Usage:
 * 1. Create an instance of the ObjectMapper class.
 * 2. Register an instance of the LocalDateTimeDeserializer class with the ObjectMapper.
 * 3. Use the ObjectMapper to deserialize JSON strings to LocalDateTime objects.
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter CUSTOM_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Deserializes a JSON string to a LocalDateTime object.
     *
     * @param p   the JsonParser object used to read the JSON string
     * @param ctx the DeserializationContext object
     * @return the LocalDateTime object deserialized from the JSON string
     * @throws IOException if an I/O error occurs
     */
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        final String dateStr = p.getValueAsString();
        return LocalDateTime.parse(dateStr, CUSTOM_DATE_TIME_FORMATTER);
    }
}
