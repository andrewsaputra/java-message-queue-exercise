package com.app.apiserver.model.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    @Id
    private int id;
    private int userId;
    private String name;
    private String description;
    private String[] images;
    private String[] compressedImages;
    private float price;
    private long createdAt;
    private long updatedAt;
}
