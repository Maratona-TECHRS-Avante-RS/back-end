package com.techrs.avante_rs.dto.user;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompleteUserResponse {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String address;

}
