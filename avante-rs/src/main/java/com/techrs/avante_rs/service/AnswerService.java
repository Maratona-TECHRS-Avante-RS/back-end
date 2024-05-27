package com.techrs.avante_rs.service;

import com.techrs.avante_rs.domain.Answer;
import com.techrs.avante_rs.dto.answer.AllAnswerResponse;
import com.techrs.avante_rs.dto.answer.AnswerResponse;
import com.techrs.avante_rs.mapper.AnswerMapper;
import com.techrs.avante_rs.repositories.AnswerRepository;
import com.techrs.avante_rs.repositories.ToolsTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ToolsTypeRepository toolsTypeRepository;

    public AllAnswerResponse getAll() {
        List<Answer> answers = answerRepository.findAll();
         return AnswerMapper.toResponse(answers);
    }

    public Page<AnswerResponse> getAllTools(Pageable pageable) {
        return toolsTypeRepository.findAll(pageable)
                .map(AnswerMapper::toResponse);
    }
}
