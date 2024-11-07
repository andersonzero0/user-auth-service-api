package com.andersonzero0.userauthservice.domain.users.dtos;

import com.andersonzero0.userauthservice.domain.users.entities.UserEntity;

import java.time.LocalDateTime;

public record UserResponseDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        String avatarUrl,
        LocalDateTime createdAt
) {
    public UserResponseDTO(UserEntity userEntity) {
        this(
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getAvatarUrl(),
                userEntity.getCreatedAt()
        );
    }
}
