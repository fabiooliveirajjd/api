package com.fabio.api.controller;

import com.fabio.api.dto.ClienteCreateDto;
import com.fabio.api.dto.ClienteResponseDto;
import com.fabio.api.dto.UsuarioResponseDto;
import com.fabio.api.dto.mapper.ClienteMapper;
import com.fabio.api.entity.Cliente;
import com.fabio.api.exception.ErrorMessage;
import com.fabio.api.jwt.JwtUserDetails;
import com.fabio.api.service.ClienteService;
import com.fabio.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Clientes", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de cliente.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    private final ClienteService service;
    private final UsuarioService usuarioService;

    @Operation(summary = "Criar um novo cliente", description = "Recurso para adicionar novo cliente vinculado ao usuário cadastrado" +
            "Requisição exige uso de um bearer token. Acesso restrito a Role='CLIENTE'",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "CPF já cadastrado no sistema",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permitido a usuario ADMIN",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create (@RequestBody @Valid ClienteCreateDto dto, @AuthenticationPrincipal JwtUserDetails userDetails) {
        Cliente cliente = ClienteMapper.toCliente(dto);
        cliente.setUsuario(usuarioService.buscarPorId(userDetails.getId()));
        service.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
    }
}