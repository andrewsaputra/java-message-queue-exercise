package com.app.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_ADD_PRODUCTS = "add-products";

    @Bean
    public Queue addProducts() {
        return new Queue(QUEUE_ADD_PRODUCTS, true);
    }
}
