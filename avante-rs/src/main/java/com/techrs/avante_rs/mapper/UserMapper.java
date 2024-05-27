package com.techrs.avante_rs.mapper;

import com.techrs.avante_rs.dto.auth.RegisterRequest;
import com.techrs.avante_rs.dto.user.CompleteUserResponse;
import com.techrs.avante_rs.security.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(RegisterRequest request){
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .cpf(request.getCpf())
                .build();
    }

    public static CompleteUserResponse toCompleteUserResponse(User user){
        return CompleteUserResponse
                .builder()
                .id(user.getId())
                .address(user.getAddress().getDescription())
                .email(user.getEmail())
                .name(user.getName())
                .cpf(user.getCpf())
                .build();
    }
}
