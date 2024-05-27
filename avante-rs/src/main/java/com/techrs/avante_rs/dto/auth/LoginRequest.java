package com.techrs.avante_rs.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "O CPF ou email deve ser preenchido")
    @Size(max = 256, message = "Este campo não pode passar de 256 caracteres")
    private String authenticator;

    @NotBlank(message = "A senha deve ser preechida")
    @Size(max = 512, message = "A senha não pode passar de 512 caracteres")
    private String password;
}
