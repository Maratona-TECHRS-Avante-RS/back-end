package com.techrs.avante_rs.security.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.techrs.avante_rs.security.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.token-secret}")
    private String secretKey;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException error){
            throw new RuntimeException("Error while generating token", error);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT
                    .require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTCreationException error){
            return null;
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusWeeks(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
