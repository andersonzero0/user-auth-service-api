package com.andersonzero0.userauthservice.services;

import com.andersonzero0.userauthservice.domain.users.entities.UserEntity;
import com.andersonzero0.userauthservice.domain.users.repositories.UserRepository;
import com.andersonzero0.userauthservice.exceptions.UserAlreadyExistsException;
import com.andersonzero0.userauthservice.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        var userByEmail = findByEmail(user.getEmail());
        var userByUsername = findByUsername(user.getUsername());

        if (userByEmail != null || userByUsername != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public UserDetails findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity updateUser(UserEntity user) {
        UserEntity existingUser = findUserById(user.getId());

        if (existingUser == null) {
            throw new UserNotFoundException("User not found");
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }

        if (user.getLastName() != null) {
            existingUser.setLastName(user.getFirstName());
        }

        if (user.getPassword() != null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            existingUser.setPassword(encryptedPassword);
        }

        return userRepository.save(existingUser);
    }
}
