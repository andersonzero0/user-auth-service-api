package com.andersonzero0.userauthservice.controllers;

import com.andersonzero0.userauthservice.domain.users.dtos.RegisterUserDTO;
import com.andersonzero0.userauthservice.domain.users.dtos.UpdateUserDTO;
import com.andersonzero0.userauthservice.domain.users.dtos.UserResponseDTO;
import com.andersonzero0.userauthservice.domain.users.entities.UserEntity;
import com.andersonzero0.userauthservice.exceptions.UserNotFoundException;
import com.andersonzero0.userauthservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO registerUser(@Valid @RequestBody RegisterUserDTO data) {
        var newUser = userService.registerUser(new UserEntity(data));
        return new UserResponseDTO(newUser);
    }

    @GetMapping
    public List<UserResponseDTO> findAllUsers() {
        var users = userService.findAll();
        return users.stream().map(UserResponseDTO::new).toList();
    }

    @GetMapping("profile/{username}")
    public UserResponseDTO findUserByUsername(@PathVariable String username) {
        var user = userService.findByUsername(username);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return new UserResponseDTO(user);
    }

    @PutMapping
    public UserResponseDTO updateUser(@Valid @RequestBody UpdateUserDTO user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity authUser = (UserEntity) authentication.getPrincipal();

        var userEntity = new UserEntity(user);
        userEntity.setId(authUser.getId());

        var updatedUser = userService.updateUser(userEntity);
        return new UserResponseDTO(updatedUser);
    }
}
