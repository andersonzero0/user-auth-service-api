package com.andersonzero0.userauthservice.domain.users.repositories;

import com.andersonzero0.userauthservice.domain.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
