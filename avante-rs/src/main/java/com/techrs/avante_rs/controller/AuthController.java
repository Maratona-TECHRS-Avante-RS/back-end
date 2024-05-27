package com.techrs.avante_rs.controller;

import com.techrs.avante_rs.dto.auth.AuthResponse;
import com.techrs.avante_rs.dto.auth.LoginRequest;
import com.techrs.avante_rs.dto.auth.RegisterRequest;
import com.techrs.avante_rs.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest auth){
        return authService.login(auth);
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest register){
        return authService.register(register);
    }
}

