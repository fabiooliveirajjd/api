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
public class UsuarioLoginDto {
    @NotBlank // O campo não pode estar em branco ou ser nulo.
    @Email(message = "O formato do email está inválido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;
    @NotBlank // O campo não pode estar em branco ou ser nulo.
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String password;
}
