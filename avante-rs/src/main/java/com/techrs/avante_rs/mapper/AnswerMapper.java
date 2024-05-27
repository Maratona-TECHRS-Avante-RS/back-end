package com.techrs.avante_rs.mapper;

import com.techrs.avante_rs.domain.Answer;
import com.techrs.avante_rs.domain.ToolsType;
import com.techrs.avante_rs.domain.TypeQuestion;
import com.techrs.avante_rs.dto.answer.AllAnswerResponse;
import com.techrs.avante_rs.dto.answer.AnswerResponse;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class AnswerMapper {


    public static AllAnswerResponse toResponse(List<Answer> answer){
        return AllAnswerResponse
                .builder()
                .numberVolunteers(builderAnswerListByType(answer, TypeQuestion.NUMBER_VOLUNTEERS))
                .urgencyType(builderAnswerListByType(answer, TypeQuestion.URGENCY_LEVEL))
                .expirationTime(builderAnswerListByType(answer, TypeQuestion.EXPIRATION_TIME))
                .helpType(builderAnswerListByType(answer, TypeQuestion.HELP_TYPE))
                .build();
    }

    public static AnswerResponse toResponse(Answer answer){
        return AnswerResponse
                .builder()
                .id(answer.getId())
                .name(answer.getName())
                .build();
    }
    public static AnswerResponse toResponse(ToolsType toolsType){
        return AnswerResponse
                .builder()
                .id(toolsType.getId())
                .name(toolsType.getName())
                .build();
    }



    private List<AnswerResponse> builderAnswerListByType(List<Answer> answerList, TypeQuestion typeQuestion){
        return answerList
                .stream()
                .filter(x -> x.getTypeQuestion().equals(typeQuestion))
                .map(AnswerMapper::toResponse)
                .collect(Collectors.toList());
    }
}
