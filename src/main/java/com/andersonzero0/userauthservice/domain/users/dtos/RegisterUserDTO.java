package com.andersonzero0.userauthservice.domain.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterUserDTO(
        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Username is required")
        @Pattern(regexp = "^[a-zA-Z0-9_.]+$",
                message = "Username can only contain alphanumeric characters, underscores and dots")
        String username,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Password is required")
        String password
) {
}
