package com.app.apiserver.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record AddProduct(
        @JsonProperty("user_id") @Positive int userId,
        @JsonProperty("product_name") @NotBlank String productName,
        @JsonProperty("product_description") @NotBlank String productDescription,
        @JsonProperty("product_images") @NotEmpty List<String> productImages,
        @JsonProperty("product_price") @Positive float productPrice
) {
}
