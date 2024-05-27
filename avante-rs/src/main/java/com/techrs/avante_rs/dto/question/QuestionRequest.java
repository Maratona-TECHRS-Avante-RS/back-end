package com.techrs.avante_rs.dto.question;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class QuestionRequest {

    private Long id;
    private Long answerId;

}
