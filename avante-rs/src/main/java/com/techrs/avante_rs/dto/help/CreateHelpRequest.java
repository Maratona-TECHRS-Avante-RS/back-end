package com.techrs.avante_rs.dto.help;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateHelpRequest {

    @Size(max = 300, message = "A descrição não pode passar de 300 caracteres")
    private String description;
    @Size(max = 512, message = "A descrição não pode passar de 512 caracteres")
    private String customAddress;
    @Size(max = 13, message = "O número não pode passar de 13 caracteres")
    private String phoneNumber;

    @NotNull(message = "Uma nível de urgência deve ser preenchido")
    private Long urgencyType;
    @NotNull(message = "Um tipo de chamado deve ser preenchido")
    private Long helpType;
    @NotNull(message = "Um tempo para o chamado expirar deve ser preenchido")
    private Long expirationTime;
    @NotNull(message = "O número de voluntários necessário deve ser preenchido")
    private Long numberVolunteers;
    private List<Long> idsTools;
}
