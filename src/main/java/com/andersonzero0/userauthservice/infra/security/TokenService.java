package com.andersonzero0.userauthservice.infra.security;

import com.andersonzero0.userauthservice.domain.users.entities.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

    public String generateToken(UserEntity user) {
        try {
            return JWT.create()
                    .withIssuer("user-auth-service")
                    .withSubject(user.getId())
                    .withExpiresAt(genExpirationDate())
                    .sign(getAlgorithm());
        }
        catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer("user-auth-service")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
