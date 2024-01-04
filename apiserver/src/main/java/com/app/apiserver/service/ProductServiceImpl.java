package com.app.apiserver.service;

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

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        return productRepository.save(newData);
    }
}
