package com.app.consumer.service;

import com.app.consumer.config.RabbitMQConfig;
import com.app.consumer.repository.ProductRepository;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener implements ChannelAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    private final ProductRepository productRepository;

    public RabbitMQListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_ADD_PRODUCTS, ackMode = "MANUAL")
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            int productId = Integer.parseInt(new String(message.getBody()));
            logger.info(String.valueOf(productId));

            System.out.println(productRepository.setCompressedImages(productId, new String[]{"placeholder1", "placeholder2"}));
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            channel.basicNack(deliveryTag, false, true);
        }
    }
}
