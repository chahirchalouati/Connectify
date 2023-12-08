package com.crcl.core.configuration;

import com.crcl.core.serializer.LocalDateTimeDeserializer;
import com.crcl.core.serializer.LocalDateTimeSerializer;
import com.crcl.core.serializer.SortDeserializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

/**
 * The CommonRabbitMQConfiguration class provides common configuration for RabbitMQ in a Spring application.
 * It includes methods for creating a JSON message converter, configuring an object mapper, and other RabbitMQ-related operations.
 */
public class CommonRabbitMQConfiguration {

    /**
     * Creates a MessageConverter that converts messages to JSON using the provided ObjectMapper.
     *
     * @param objectMapper the ObjectMapper to be used for JSON conversion
     * @return a MessageConverter that converts messages to JSON
     */
    @Bean
    public MessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    /**
     * Creates and configures an instance of the ObjectMapper class.
     *
     * @return The configured ObjectMapper instance.
     */
    @Bean
    public ObjectMapper objectMapper() {
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        simpleModule.addDeserializer(Sort.class, new SortDeserializer());
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());

        return new ObjectMapper()
                .registerModule(simpleModule)
                .registerModule(new PageJacksonModule())
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setVisibility(VisibilityChecker.Std.defaultInstance()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY));
    }
}
