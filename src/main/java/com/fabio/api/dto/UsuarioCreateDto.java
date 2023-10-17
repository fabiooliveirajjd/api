package com.fabio.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDto {

    @NotBlank //SE NÃO ESTÁ NULL, SE NÃO ESTÁ VAZIO E NÃO PODE TER CARACTER VAZIO
    @Email(message = "O formato do email está inválido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;
    //    "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$" ESSA EXPRESSÃO INDICA EU UM EMAIL NÃO PODE SER CRIADO DA SEGUINTE FORMA:  "username": "a@mail"
    @NotBlank
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String password;
}
