package com.app.consumer.service;

import com.app.consumer.config.AppConfig;
import com.app.consumer.config.RabbitMQConfig;
import com.app.consumer.model.entity.Product;
import com.app.consumer.repository.ProductRepository;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class RabbitMQListener implements ChannelAwareMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    private final AppConfig appConfig;
    private final ProductRepository productRepository;

    @Autowired
    public RabbitMQListener(AppConfig appConfig, ProductRepository productRepository) {
        this.appConfig = appConfig;
        this.productRepository = productRepository;
    }

    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_ADD_PRODUCTS, ackMode = "MANUAL")
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            int productId = Integer.parseInt(new String(message.getBody()));
            logger.info(String.valueOf(productId));

            Optional<Product> product = productRepository.findById(productId);
            if (product.isEmpty()) {
                channel.basicAck(deliveryTag, false);
                return;
            }

            List<String> compressedImages = new ArrayList<>();
            for (String imageName : product.get().getImages()) {
                String outputName = compressAndSave(imageName);
                compressedImages.add(outputName);
            }
            if (!compressedImages.isEmpty()) {
                productRepository.setCompressedImages(productId, compressedImages.toArray(new String[0]));
            }

            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            channel.basicNack(deliveryTag, false, true);
        }
    }

    private String compressAndSave(String imageName) throws Exception {
        String inputPath = appConfig.getImageDir() + imageName;
        String outputName = "compressed_" + imageName;
        String outputPath = appConfig.getCompressedImageDir() + outputName;

        try (InputStream is = new FileInputStream(inputPath)) {
            BufferedImage bufferedImage = ImageIO.read(is);
            String format = StringUtils.getFilenameExtension(inputPath);
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);
            if (!writers.hasNext()) {
                throw new RuntimeException("no writer available for format " + format);
            }

            File outputFile = new File(outputPath);
            try (ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile)) {
                ImageWriter imageWriter = writers.next();
                ImageWriteParam param = imageWriter.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(appConfig.getCompressionQuality());

                imageWriter.setOutput(ios);
                imageWriter.write(
                        null,
                        new IIOImage(bufferedImage, null, null),
                        param
                );

                ios.flush();
                imageWriter.dispose();
            }
        }

        return outputName;
    }
}
