package com.app.apiserver.model.entity;

import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private long createdAt;
    private long updatedAt;


}
