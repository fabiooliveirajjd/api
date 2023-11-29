/**
 * DTO (Data Transfer Object) para a atualização da senha do usuário.
 *
 * Esta classe é usada para transferir dados de atualização da senha do usuário para a aplicação. Ela contém campos
 * para a senha atual, nova senha e confirmação de senha, e possui anotações de validação para garantir que os dados inseridos
 * atendam aos requisitos específicos.
 */
package com.fabio.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSenhaDto {

    @NotBlank // O campo não pode estar em branco ou ser nulo.
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String senhaAtual; // A senha atual do usuário.

    @NotBlank // O campo não pode estar em branco ou ser nulo.
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String novaSenha; // A nova senha desejada.

    @NotBlank // O campo não pode estar em branco ou ser nulo.
    @Size(min = 6, max = 6, message = "A senha deve conter exatamente 6 caracteres")
    private String confirmaSenha; // A confirmação da nova senha.
}
