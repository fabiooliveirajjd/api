/**
 * Entidade de Usuário.
 *
 * Esta classe representa uma entidade de usuário no sistema. Ela é mapeada para a tabela "usuarios" no banco de dados
 * e contém informações sobre o nome de usuário, senha, papel (role), datas de criação e modificação, criador e
 * modificador.
 */
package com.fabio.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENTE;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @Column(name = "criado_por")
    private String criadorPor;

    @Column(name = "modificado_por")
    private String modificadoPor;

    /**
     * Enumeração que representa os papéis (roles) possíveis para um usuário.
     */
    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }
}
