package com.techrs.avante_rs.service;

import com.techrs.avante_rs.domain.Address;
import com.techrs.avante_rs.dto.auth.LoginRequest;
import com.techrs.avante_rs.dto.auth.AuthResponse;
import com.techrs.avante_rs.dto.auth.RegisterRequest;
import com.techrs.avante_rs.dto.mapApi.CoordinateResponse;
import com.techrs.avante_rs.mapper.AuthMapper;
import com.techrs.avante_rs.mapper.UserMapper;
import com.techrs.avante_rs.mapper.UserSecurityMapper;
import com.techrs.avante_rs.security.config.user.UserSecurity;
import com.techrs.avante_rs.security.domain.User;
import com.techrs.avante_rs.security.domain.UserRole;
import com.techrs.avante_rs.security.repository.UserRepository;
import com.techrs.avante_rs.security.service.TokenService;
import com.techrs.avante_rs.service.core.ValidUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapApiService mapApiService;

    @Autowired
    private ValidUserService validUserService;

    @Autowired
    private TokenService tokenService;


    public AuthResponse login(LoginRequest request) {

        UsernamePasswordAuthenticationToken user
                = new UsernamePasswordAuthenticationToken(request.getAuthenticator(), request.getPassword());
        var auth = authenticationManager.authenticate(user);

        User userLogged = UserSecurityMapper.toUser((UserSecurity) auth.getPrincipal());
        String token = tokenService.generateToken(userLogged);
        return AuthMapper.toReponse(userLogged, token);
    }

    public AuthResponse register(RegisterRequest request) {
        validUserService.isEmailUsed(request.getEmail());
        validUserService.isCpfUsed(request.getCpf());

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
        User newUser = UserMapper.toUser(request);
        newUser.setRole(UserRole.USER);
        newUser.setPassword(encryptedPassword);

        Address address = new Address();
        CoordinateResponse addressCoordinates = mapApiService.getCoordinateFromAddress(request.getAddress());
        address.setDescription(request.getAddress());
        address.setCoordinateX(addressCoordinates.getAxleX());
        address.setCoordinateY(addressCoordinates.getAxleY());

        newUser.insertAddress(address);
        userRepository.save(newUser);

        LoginRequest userCredentials =
                new LoginRequest(request.getEmail(), request.getPassword());
        return login(userCredentials);
    }
}
