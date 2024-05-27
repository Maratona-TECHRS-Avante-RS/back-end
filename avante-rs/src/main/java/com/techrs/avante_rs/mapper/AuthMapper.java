package com.techrs.avante_rs.mapper;

import com.techrs.avante_rs.dto.auth.AuthResponse;
import com.techrs.avante_rs.security.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthMapper {

    public static AuthResponse toReponse(User user, String token){
        return AuthResponse.builder()
                .role(user.getRole())
                .token(token)
                .build();
    }
}
