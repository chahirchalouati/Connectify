package com.crcl.core.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter CUSTOM_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        final String dateStr = p.getValueAsString();
        return LocalDateTime.parse(dateStr, CUSTOM_DATE_TIME_FORMATTER);
    }
}
