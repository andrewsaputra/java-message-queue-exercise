package com.app.apiserver.service;

import com.app.apiserver.config.RabbitMQConfig;
import com.app.apiserver.model.dto.AddProduct;
import com.app.apiserver.model.entity.Product;
import com.app.apiserver.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final IMQPublisher mqPublisher;

    public ProductServiceImpl(ProductRepository productRepository, IMQPublisher mqPublisher) {
        this.productRepository = productRepository;
        this.mqPublisher = mqPublisher;
    }

    @Override
    public Optional<Product> getProduct(int productId) throws Exception {
        return productRepository.findById(productId);
    }

    @Override
    public void deleteProduct(int productId) throws Exception {
        productRepository.deleteById(productId);
    }

    @Override
    public Product addProduct(AddProduct dto) throws Exception {
        Product newData = new Product(
                0,
                dto.userId(),
                dto.productName(),
                dto.productDescription(),
                dto.productImages(),
                Collections.emptyList(),
                dto.productPrice(),
                System.currentTimeMillis(),
                0L
        );
        Product newProduct = productRepository.save(newData);
        mqPublisher.publish(RabbitMQConfig.QUEUE_ADD_PRODUCTS, String.valueOf(newProduct.getId()));
        return newProduct;
    }
}
