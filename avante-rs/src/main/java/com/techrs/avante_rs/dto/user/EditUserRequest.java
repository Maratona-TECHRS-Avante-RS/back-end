package com.techrs.avante_rs.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


@Getter
@Setter
public class EditUserRequest {
    @NotBlank(message = "O nome deve ser preenchido")
    @Size(max = 80, message = "O nome não pode passar de 80 caracteres")
    private String name;
    @NotBlank(message = "O endereço deve ser preenchido")
    @Size(max = 512, message = "O endereço não pode passar de 512 caracteres")
    private String address;
    @CPF(message = "Digite um CPF válido")
    @NotBlank(message = "O CPF deve ser preenchido")
    @Size(max = 20, message = "O CPF não pode passar de 11 caracteres")
    private String cpf;
    @NotBlank(message = "O email deve ser preenchido")
    @Email(message = "Insira um email válido")
    @Size(max = 256, message = "O email não pode passar de 256 caracteres")
    private String email;

    @Size(max = 512, message = "A senha atual não pode passar de 512 caracteres")
    private String activePassword;
    @Size(max = 512, message = "A nova senha não pode passar de 512 caracteres")
    private String newPassword;

}

