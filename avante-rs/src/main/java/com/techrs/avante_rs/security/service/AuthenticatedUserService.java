package com.techrs.avante_rs.security.service;

import com.techrs.avante_rs.security.config.user.UserSecurity;
import com.techrs.avante_rs.security.domain.User;
import com.techrs.avante_rs.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class AuthenticatedUserService {

    @Autowired
    private UserRepository userRepository;

    public Long getIdUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object user = authentication.getPrincipal();

        if (user instanceof UserSecurity) {
            return Long.valueOf(((UserSecurity) user).getId());
        }

        return null;
    }

    public User getUser() {
        Long id = getIdUser();

        if (isNull(id)) {
            return null;
        }

        return userRepository.findById(id).orElse(null);
    }
}