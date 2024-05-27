package com.techrs.avante_rs.mapper;

import com.techrs.avante_rs.security.config.user.UserSecurity;
import com.techrs.avante_rs.security.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserSecurityMapper {

    public static User toUser(UserSecurity userSecurity){
        return User.builder()
                .id(Long.valueOf(userSecurity.getId()))
                .email(userSecurity.getUsername())
                .role(userSecurity.getRole())
                .build();
    }

}
