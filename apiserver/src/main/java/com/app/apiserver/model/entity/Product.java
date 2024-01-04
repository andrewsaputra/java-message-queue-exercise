package com.app.apiserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@AllArgsConstructor
@Table("products")
public class Product {
    @Id
    private int id;
    private int userId;
    private String name;
    private String description;
    private List<String> images;
    private List<String> compressedImages;
    private float price;

    @Column("created_at")
    private long createdAt;
    @Column("updated_at")
    private long updatedAt;
}
