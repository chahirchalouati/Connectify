package com.crcl.core.configuration;

import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

/**
 * The CommonQueueConfiguration class is an abstract class that provides a configuration for common RabbitMQ queues.
 * It is meant to be extended by specific implementations to define the queues and their properties.
 * In order to use this configuration, make sure to annotate your class with @Import(CommonRabbitMQConfiguration.class).
 *
 * The class provides two bean definitions:
 * - initDeclarables(): This bean initializes the RabbitMQ queues based on the values provided by the getQueues() method.
 *                      It returns a Declarables object containing the queue declarations.
 * - rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter): This bean creates a RabbitTemplate
 *                      used for sending and receiving messages from RabbitMQ. It sets the provided ConnectionFactory as the underlying
 *                      connection factory and uses the provided MessageConverter for converting the messages to and from JSON format.
 *
 * To use this configuration, you need to implement the getQueues() method in your subclass, which should return a list of strings
 * representing the names of the queues to be declared.
 *
 * This configuration also requires a CommonRabbitMQConfiguration class, which provides the necessary beans for message conversion
 * and serialization.
 */
@Import(CommonRabbitMQConfiguration.class)
public abstract class CommonQueueConfiguration {

    /**
     *
     */
    protected List<Queue> queues = new ArrayList<>();

    /**
     * This method returns a list of queues to be declared in the RabbitMQ configuration.
     *
     * @return a list of strings representing the names of the queues
     */
    public abstract List<String> getQueues();

    /**
     * Initializes RabbitMQ queues based on the values provided by the getQueues() method.
     * Returns a Declarables object containing the queue declarations.
     * This method is part of the CommonQueueConfiguration class and needs to be annotated with @Bean.
     *
     * @return a Declarables object containing the queue declarations
     */
    @Bean
    public Declarables initDeclarables() {
        queues = getQueues().stream()
                .map(queue -> new Queue(queue, true, false, false))
                .toList();
        return new Declarables(queues);
    }

    /**
     * Creates a RabbitTemplate for sending and receiving messages from RabbitMQ.
     *
     * This method initializes a RabbitTemplate instance with the provided ConnectionFactory and MessageConverter.
     * The ConnectionFactory is used as the underlying connection factory for the RabbitTemplate, while the MessageConverter
     * is used for converting the messages to and from JSON format.
     *
     * @param connectionFactory the ConnectionFactory to be used by the RabbitTemplate
     * @param jsonMessageConverter the MessageConverter to be used by the RabbitTemplate
     * @return the initialized RabbitTemplate instance
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter);
        return rabbitTemplate;
    }
}
