package com.app.apiserver.model.dto;

import lombok.Data;

@Data
public class AddProduct {
    private String userId;
    private String productName;
    private String productDescription;
    private String productImages;
    private float productPrice;
}
