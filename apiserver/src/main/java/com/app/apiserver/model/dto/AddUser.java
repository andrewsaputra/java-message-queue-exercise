package com.app.apiserver.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AddUser(
        @NotBlank String name,
        @NotBlank @Email String email
) {
}
