package com.andersonzero0.userauthservice.domain.users.entities;

import com.andersonzero0.userauthservice.domain.users.dtos.RegisterUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "users_tb")
@Entity(name = "users_tb")
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "username", unique = true)
    private String username;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(nullable = false, name = "password")
    private String password;

    @CreatedDate
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    public UserEntity(RegisterUserDTO registerUserDTO) {
        this.email = registerUserDTO.email();
        this.username = registerUserDTO.username();
        this.firstName = registerUserDTO.firstName();
        this.lastName = registerUserDTO.lastName();
        this.password = registerUserDTO.password();
    }
}
