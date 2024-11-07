package com.andersonzero0.userauthservice.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message == null ? "User already exists" : message);
    }
}
