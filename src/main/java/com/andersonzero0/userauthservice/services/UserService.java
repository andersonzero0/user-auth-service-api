package com.andersonzero0.userauthservice.services;

import com.andersonzero0.userauthservice.domain.users.entities.UserEntity;
import com.andersonzero0.userauthservice.domain.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
