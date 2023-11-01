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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @CreatedBy
    @Column(name = "criado_por")
    private String criadorPor;

    @LastModifiedBy
    @Column(name = "modificado_por")
    private String modificadoPor;

    /**
     * Enumeração que representa os papéis (roles) possíveis para um usuário.
     */
    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }
}
