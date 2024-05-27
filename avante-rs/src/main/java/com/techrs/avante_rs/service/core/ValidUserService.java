package com.techrs.avante_rs.service.core;

import com.techrs.avante_rs.security.domain.User;
import com.techrs.avante_rs.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class ValidUserService {

    @Autowired
    private UserRepository userRepository;

    public User getById(Long id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não encontrado"));
    }

    public void isEmailUsed(String email){
        boolean isEmailExists = userRepository.existsByEmail(email);
        if(isEmailExists){
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Este email já está em uso");
        }
    }

    public void isCpfUsed(String cpf){
        boolean isCpfUsed = userRepository.existsByCpf(cpf);
        if(isCpfUsed){
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Este CPF já está em uso");
        }
    }
}

