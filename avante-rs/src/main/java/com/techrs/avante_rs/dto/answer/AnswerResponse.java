package com.techrs.avante_rs.dto.answer;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AnswerResponse {
    private String name;
    private Long id;
}
