package com.app.apiserver.service;

import com.app.apiserver.model.basic.MQPublish;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class RabbitMQPublisher implements IMQPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(String queue, String message) throws Exception {
        MessageProperties properties = new MessageProperties();
        properties.setContentType("text/plain");

        Message payload = MessageBuilder
                .withBody(message.getBytes(StandardCharsets.UTF_8))
                .andProperties(properties)
                .build();
        rabbitTemplate.convertAndSend(queue, payload);
    }
}
