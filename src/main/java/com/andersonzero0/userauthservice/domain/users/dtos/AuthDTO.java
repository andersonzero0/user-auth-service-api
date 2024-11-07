package com.andersonzero0.userauthservice.domain.users.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {
}
