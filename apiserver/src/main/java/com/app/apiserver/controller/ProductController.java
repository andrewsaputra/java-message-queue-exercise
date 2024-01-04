package com.app.apiserver.controller;

import com.app.apiserver.model.basic.ApiResponse;
import com.app.apiserver.model.dto.AddProduct;
import com.app.apiserver.model.entity.Product;
import com.app.apiserver.service.IProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ApiResponse getProduct(@PathVariable("id") int productId) throws Exception {
        Optional<Product> product = productService.getProduct(productId);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ApiResponse("data found", product.get());
    }

    @DeleteMapping(path = "/{id}")
    public ApiResponse deleteProduct(@PathVariable("id") int productId) throws Exception {
        productService.deleteProduct(productId);
        return new ApiResponse("data removal processed", null);
    }

    @PostMapping
    public ApiResponse addProduct(@Valid @RequestBody AddProduct dto) throws Exception {
        try {
            Product newProduct = productService.addProduct(dto);
            return new ApiResponse("data added", newProduct);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            if (e.getCause() instanceof DataIntegrityViolationException) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
