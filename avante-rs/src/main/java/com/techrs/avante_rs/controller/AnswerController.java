package com.techrs.avante_rs.controller;

import com.techrs.avante_rs.dto.answer.AllAnswerResponse;
import com.techrs.avante_rs.dto.answer.AnswerResponse;
import com.techrs.avante_rs.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public AllAnswerResponse getAll(){
        return answerService.getAll();
    }

    @GetMapping("/tools")
    public Page<AnswerResponse> getAllTools(Pageable pageable){
        return answerService.getAllTools(pageable);
    }

}
