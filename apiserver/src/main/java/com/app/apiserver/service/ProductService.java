package com.app.apiserver.service;

import com.app.apiserver.model.basic.ServiceResponse;
import com.app.apiserver.model.dto.AddProduct;

public interface ProductService {
    ServiceResponse getProduct(int productId);
    ServiceResponse deleteProduct(int productId);
    ServiceResponse addProduct(AddProduct dto);
}
