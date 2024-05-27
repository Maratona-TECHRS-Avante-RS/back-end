package com.techrs.avante_rs.dto.auth;

import com.techrs.avante_rs.security.domain.UserRole;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private UserRole role;
    private String token;
}
