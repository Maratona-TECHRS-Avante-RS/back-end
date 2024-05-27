package com.techrs.avante_rs.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    USER(Name.USER);

    public static class Name {
        public static final String USER = "ROLE_USER";
    }

    private final String roleName;
}
