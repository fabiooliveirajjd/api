/**
 * Repositório de dados para entidades de usuário.
 * <p>
 * Este repositório fornece métodos para realizar operações de acesso a dados relacionadas às entidades de usuário,
 * como salvar, buscar por ID e excluir. É baseado na interface JpaRepository do Spring Data JPA e trabalha com a entidade Usuario
 * com identificadores do tipo Long.
 */
package com.fabio.api.repository;


import com.fabio.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("select u.role from Usuario u where u.username like :username")
    Usuario.Role findRoleByUsername(String username);
}