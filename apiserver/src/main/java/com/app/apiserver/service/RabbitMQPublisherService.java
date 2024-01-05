package com.app.apiserver.service;

import com.app.apiserver.model.basic.MQPublish;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class RabbitMQPublisherService implements IMQPublisherService {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(MQPublish payload) throws Exception {
        MessageProperties properties = new MessageProperties();
        properties.setContentType("text/plain");

        Message message = MessageBuilder
                .withBody(payload.message().getBytes(StandardCharsets.UTF_8))
                .andProperties(properties)
                .build();

        rabbitTemplate.convertAndSend(payload.queue(), message);
    }
}
