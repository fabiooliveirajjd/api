/**
 * Repositório de dados para entidades de usuário.
 *
 * Este repositório fornece métodos para realizar operações de acesso a dados relacionadas às entidades de usuário,
 * como salvar, buscar por ID e excluir. É baseado na interface JpaRepository do Spring Data JPA e trabalha com a entidade Usuario
 * com identificadores do tipo Long.
 */
package com.fabio.api.repository;

import com.fabio.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
