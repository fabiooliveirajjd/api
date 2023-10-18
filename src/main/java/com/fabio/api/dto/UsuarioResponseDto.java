/**
 * DTO (Data Transfer Object) para a resposta de informações do usuário.
 *
 * Esta classe é usada para transferir informações de resposta relacionadas a um usuário da aplicação. Ela contém campos
 * para o ID do usuário, nome de usuário (email) e papel (role) do usuário na aplicação.
 */
package com.fabio.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {

    private Long id;         // O ID do usuário.
    private String username; // O nome de usuário (email).
    private String role;     // O papel (role) do usuário na aplicação.
}
