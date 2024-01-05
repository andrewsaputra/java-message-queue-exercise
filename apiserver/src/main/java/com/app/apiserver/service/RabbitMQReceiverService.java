package com.app.apiserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiverService implements IMQReceiverService{
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiverService.class);

    @Override
    @RabbitListener(queues = "test-queue", ackMode = "AUTO")
    public void receive(String message) {
        logger.info(message);
    }
}
