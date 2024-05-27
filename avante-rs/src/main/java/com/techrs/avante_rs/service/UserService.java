package com.techrs.avante_rs.service;

import com.techrs.avante_rs.domain.Address;
import com.techrs.avante_rs.dto.IdResponse;
import com.techrs.avante_rs.dto.mapApi.CoordinateResponse;
import com.techrs.avante_rs.dto.user.CompleteUserResponse;
import com.techrs.avante_rs.dto.user.EditUserRequest;
import com.techrs.avante_rs.mapper.IdCommonMapper;
import com.techrs.avante_rs.mapper.UserMapper;
import com.techrs.avante_rs.security.domain.User;
import com.techrs.avante_rs.security.repository.UserRepository;
import com.techrs.avante_rs.security.service.AuthenticatedUserService;
import com.techrs.avante_rs.service.core.ValidUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapApiService mapApiService;
    @Autowired
    private ValidUserService validUserService;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Transactional
    public void deleteLogged() {
        Long userId = authenticatedUserService.getIdUser();
        userRepository.deleteById(userId);
    }


    public CompleteUserResponse getLogged() {
        User userLogged = authenticatedUserService.getUser();
        return UserMapper.toCompleteUserResponse(userLogged);
    }

    @Transactional
    public IdResponse editUserLogged(EditUserRequest request) {

        User userLogged = authenticatedUserService.getUser();

        if(!Objects.equals(userLogged.getEmail(), request.getEmail())){
            validUserService.isEmailUsed(request.getEmail());
            userLogged.setEmail(request.getEmail());
        }
        if(!Objects.equals(userLogged.getCpf(), request.getCpf())){
            validUserService.isCpfUsed(request.getCpf());
            userLogged.setCpf(request.getCpf());
        }
        userLogged.setName(request.getName());

        if(request.getActivePassword() != null && !request.getActivePassword().isBlank()){
            if(request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if(passwordEncoder.matches(request.getActivePassword(), userLogged.getPassword())){

                    String encryptedPassword = passwordEncoder.encode(request.getNewPassword());
                    userLogged.setPassword(encryptedPassword);
                }else{
                    throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Senha inserida não está correta");
                }
            }
        }

        if(!Objects.equals(userLogged.getAddress().getDescription(), request.getAddress())){
            Address address = new Address();
            CoordinateResponse addressCoordinates = mapApiService.getCoordinateFromAddress(request.getAddress());
            address.setDescription(request.getAddress());
            address.setCoordinateX(addressCoordinates.getAxleX());
            address.setCoordinateY(addressCoordinates.getAxleY());
            userLogged.insertAddress(address);
        }

        //TODO login ao final
        userRepository.save(userLogged);
        return IdCommonMapper.toIdResponse(userLogged);
    }
}
