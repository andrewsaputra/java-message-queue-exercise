package com.app.apiserver.controller;

import com.app.apiserver.model.basic.ApiResponse;
import com.app.apiserver.model.dto.AddProduct;
import com.app.apiserver.model.entity.Product;
import com.app.apiserver.service.IProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable("id") int productId) throws Exception {
        Optional<Product> product = productService.getProduct(productId);

        HttpStatus statusCode;
        ApiResponse apiResponse;
        if (product.isPresent()) {
            statusCode = HttpStatus.OK;
            apiResponse = new ApiResponse("", product.get());
        } else {
            statusCode = HttpStatus.NOT_FOUND;
            apiResponse = new ApiResponse("data not found", null);
        }

        return ResponseEntity
                .status(statusCode)
                .body(apiResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") int productId) throws Exception {
        boolean success = productService.deleteProduct(productId);

        HttpStatus statusCode;
        ApiResponse apiResponse;
        if (success) {
            statusCode = HttpStatus.OK;
            apiResponse = new ApiResponse("data removed", null);
        } else {
            statusCode = HttpStatus.NOT_FOUND;
            apiResponse = new ApiResponse("data not found", null);
        }

        return ResponseEntity
                .status(statusCode)
                .body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody AddProduct dto) throws Exception {
        Product newProduct = productService.addProduct(dto);
        ApiResponse apiResponse = new ApiResponse("data added", newProduct);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }
}
