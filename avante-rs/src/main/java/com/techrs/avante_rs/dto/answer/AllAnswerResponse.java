package com.techrs.avante_rs.dto.answer;
import lombok.Builder;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AllAnswerResponse {

    private List<AnswerResponse> urgencyType;
    private List<AnswerResponse> helpType;
    private List<AnswerResponse> expirationTime;
    private List<AnswerResponse> numberVolunteers;

}
