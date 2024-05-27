package com.techrs.avante_rs.service.core;

import com.techrs.avante_rs.domain.Help;
import com.techrs.avante_rs.repositories.HelpRepository;
import com.techrs.avante_rs.security.service.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidHelpService {

    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private AuthenticatedUserService userService;

    public void helpExistsById(Long id){
        var exists = helpRepository.existsById(id);
        if(!exists){
            throw new ResponseStatusException(NOT_FOUND, "Chamado não encontrado");
        }
    }

    public Help getHelpById(Long id){
        return helpRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Chamado não encontrado"));
    }

    public void isUserCreatorByHelp(Help help){
        if(!Objects.equals(help.getUserCreator().getId(), userService.getIdUser())){
            throw new ResponseStatusException(FORBIDDEN, "Sem permissão");
        }
    }

    public void isUserCreatorByIdHelp(Long helpId){
        var exists = helpRepository.existsByIdAndUserCreatorId(helpId, userService.getIdUser());
        if(!exists){
            throw new ResponseStatusException(FORBIDDEN, "Sem permissão");
        }
    }

}
