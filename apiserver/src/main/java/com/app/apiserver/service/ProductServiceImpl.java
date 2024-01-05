package com.app.apiserver.service;

import com.app.apiserver.model.basic.MQPublish;
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
    private final IMQPublisherService mqPublisher;

    public ProductServiceImpl(ProductRepository productRepository, IMQPublisherService mqPublisher) {
        this.productRepository = productRepository;
        this.mqPublisher = mqPublisher;
    }

    @Override
    public Optional<Product> getProduct(int productId) throws Exception {
        mqPublisher.publish(new MQPublish("test-queue", "sample message"));
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
        return productRepository.save(newData);
    }
}
