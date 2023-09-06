package com.fabio.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
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

    @Enumerated(EnumType.STRING)//TRANSFORMA O NOME DA CONSTANTE (0 ROLE_ADMIN E 1 ROLE_CLIENTE) EM UMA STRING PARA SALVAR NO BANCO DE DADOS
    @Column(name = "role", nullable = false, length = 25)
    private Role role;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;//auditoria

    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;//auditoria

    @Column(name = "criado_por")
    private String criadorPor;//auditoria

    @Column(name = "modificado_por")
    private String modificadoPor;//auditoria
    public enum Role {
        ROLE_ADMIN, ROLE_CLIENTE
    }
}
