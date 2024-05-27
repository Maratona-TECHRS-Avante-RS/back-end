package com.techrs.avante_rs.service.core;

import com.techrs.avante_rs.domain.Answer;
import com.techrs.avante_rs.domain.TypeQuestion;
import com.techrs.avante_rs.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidAnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer getAnswerByIdAndType(Long id, TypeQuestion type){
        return answerRepository.findByIdAndTypeQuestion(id, type)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Opção de resposta não foi encontrada"));
    }
}
