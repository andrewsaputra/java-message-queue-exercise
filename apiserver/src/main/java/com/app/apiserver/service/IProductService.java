package com.app.apiserver.service;

import com.app.apiserver.model.dto.AddProduct;
import com.app.apiserver.model.entity.Product;

import java.util.Optional;

public interface IProductService {
    Optional<Product> getProduct(int productId) throws Exception;

    void deleteProduct(int productId) throws Exception;

    Product addProduct(AddProduct dto) throws Exception;
}
