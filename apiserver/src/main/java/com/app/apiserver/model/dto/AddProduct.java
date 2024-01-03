package com.app.apiserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record AddProduct(
        @Positive int userId,
        @NotBlank String productName,
        @NotBlank String productDescription,
        @NotEmpty List<String> productImages,
        @Positive float productPrice
) {
}
