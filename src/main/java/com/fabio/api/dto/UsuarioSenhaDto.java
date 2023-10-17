package com.fabio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSenhaDto {

    @NotBlank
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String senhaAtual;
    @NotBlank
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String novaSenha;
    @NotBlank
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String confirmaSenha;
}
