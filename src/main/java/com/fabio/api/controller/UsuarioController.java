/**
 * Controlador para operações relacionadas a usuários na API.
 *
 * Este controlador gerencia as operações CRUD (Create, Read, Update, Delete) para entidades de usuário
 * na API. Ele lida com a criação de usuários, recuperação de informações de usuários por ID, atualização de senhas de usuários
 * e busca de todos os usuários.
 */
package com.fabio.api.controller;

import com.fabio.api.dto.UsuarioCreateDto;
import com.fabio.api.dto.UsuarioResponseDto;
import com.fabio.api.dto.UsuarioSenhaDto;
import com.fabio.api.dto.mapper.UsuarioMapper;
import com.fabio.api.entity.Usuario;
import com.fabio.api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Cria um novo usuário com base nos dados fornecidos no corpo da requisição.
     *
     * @param createDto Os dados do novo usuário a serem criados.
     * @return ResponseEntity contendo os dados do usuário recém-criado e o status HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario newUsuario = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(newUsuario));
    }

    /**
     * Recupera as informações de um usuário com base no seu ID.
     *
     * @param id O ID do usuário a ser recuperado.
     * @return ResponseEntity contendo os dados do usuário e o status HTTP 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    /**
     * Atualiza a senha de um usuário com base no seu ID.
     *
     * @param id O ID do usuário cuja senha será atualizada.
     * @param dto Os dados da nova senha a ser atualizada.
     * @return ResponseEntity com status HTTP 204 (No Content) indicando que a senha foi atualizada com sucesso.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    /**
     * Recupera todos os usuários cadastrados.
     *
     * @return ResponseEntity contendo uma lista de dados de usuário e o status HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> buscarTodos() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
