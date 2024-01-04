package com.app.apiserver.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("users")
public class User {
    @Id
    private int id;
    private String name;
    private String email;

    @Column("created_at")
    private long createdAt;
    @Column("updated_at")
    private long updatedAt;
}
