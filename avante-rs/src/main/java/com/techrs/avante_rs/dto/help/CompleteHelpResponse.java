package com.techrs.avante_rs.dto.help;

import com.techrs.avante_rs.dto.answer.AnswerResponse;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CompleteHelpResponse {
    private Long id;
    private Integer activeVolunteers;
    private String createdAt;
    private String customAddress;
    private String defaultAddress;
    private String distance;
    private String description;
    private String phoneNumber;
    private boolean isUserVoluntary;
    private boolean isUserCreator;
    private AnswerResponse helpType;
    private AnswerResponse urgencyLevel;
    private AnswerResponse expirationTime;
    private AnswerResponse numberVolunteers;
    private List<AnswerResponse> toolsOptions;
}
