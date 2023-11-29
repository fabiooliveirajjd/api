/**
 * Controlador para operações relacionadas a usuários na API.
 *
 * Este controlador gerencia as operações CRUD (Create, Read, Update, Delete) para entidades de usuário
 * na API. Ele lida com a criação de usuários, recuperação de informações de usuários por ID, atualização de senhas de usuários
 * e busca de todos os usuários.
 */
package com.fabio.api.web.controller;

import com.fabio.api.web.dto.UsuarioCreateDto;
import com.fabio.api.web.dto.UsuarioResponseDto;
import com.fabio.api.web.dto.UsuarioSenhaDto;
import com.fabio.api.web.dto.mapper.UsuarioMapper;
import com.fabio.api.entity.Usuario;
import com.fabio.api.web.exception.ErrorMessage;
import com.fabio.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de usuário.")
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
    @Operation(summary = "Criar um novo usuário", description = "Recurso para adicionar novo usuário",
        responses = {
                @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
                @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
        })
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
    @Operation(summary = "Recuperar um usuário pelo id", description = "Requisição exige um Bearer token. Acesso restrito a ADMIN E CLIENTE.",
            security = @SecurityRequirement(name = "bearer-jwt"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('CLIENTE') AND #id == authentication.principal.id)") // Apenas usuários com papel ADMIN podem acessar este recurso.
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
    @Operation(summary = "Atualizar senha", description = "Requisição exige um Bearer token. Acesso restrito a ADMIN E CLIENTE.",
            security = @SecurityRequirement(name = "bearer-jwt"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "400", description = "Senha não confere",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE') AND #id == authentication.principal.id")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    /**
     * Recupera todos os usuários cadastrados.
     *
     * @return ResponseEntity contendo uma lista de dados de usuário e o status HTTP 200 (OK).
     */
    @Operation(summary = "Listar todos os usuários CADASTRADOS", description = "Requisição exige um Bearer token. Acesso restrito a ADMIN.",
            security = @SecurityRequirement(name = "bearer-jwt"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista com todos os usuários cadastrados",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class)))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar este recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Apenas usuários com papel ADMIN podem acessar este recurso.
    public ResponseEntity<List<UsuarioResponseDto>> buscarTodos() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
