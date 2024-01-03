package com.app.apiserver.service;

import com.app.apiserver.model.dto.AddProduct;
import com.app.apiserver.model.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);


    @Override
    public Optional<Product> getProduct(int productId) throws Exception {
        Optional<Product> result = Optional.empty();
        if (productId == 404) {
            return result;
        }

        if (productId == 500) {
            throw new RuntimeException("sample exception");
        }

        result = Optional.of(
                new Product(
                        productId,
                        999,
                        "product name",
                        "product description",
                        Arrays.asList("image1", "image2"),
                        Arrays.asList("compressedImage1", "compressedImage2"),
                        100.0f,
                        System.currentTimeMillis(),
                        0L
                )
        );

        return result;
    }

    @Override
    public boolean deleteProduct(int productId) throws Exception {
        return productId != 404;
    }

    @Override
    public Product addProduct(AddProduct dto) throws Exception {
        Product result = new Product(
                111,
                dto.userId(),
                dto.productName(),
                dto.productDescription(),
                dto.productImages(),
                Collections.emptyList(),
                dto.productPrice(),
                System.currentTimeMillis(),
                0L
        );

        return result;
    }
}
