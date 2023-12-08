package com.crcl.core.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Sort;

import java.io.IOException;

/**
 * SortDeserializer is a custom deserializer for Sort objects. It extends the JsonDeserializer class and is used by the ObjectMapper to deserialize JSON nodes into Sort objects.
 */
public class SortDeserializer extends JsonDeserializer<Sort> {
    /**
     * Deserializes a JSON node into a Sort object.
     *
     * @param p the JsonParser object that provides access to the JSON node
     * @param ctxt the DeserializationContext object for deserialization configuration
     * @return a Sort object constructed from the sorting information in the JSON node
     * @throws IOException if an I/O error occurs during deserialization
     */
    @Override
    public Sort deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String property = node.get("property").asText();
        String direction = node.get("direction").asText();

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Sort.Order order = new Sort.Order(sortDirection, property);

        return Sort.by(order);
    }
}