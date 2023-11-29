package com.fabio.api.web.dto;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ClienteResponseDto {
    private Long id;
    private String nome;
    private String cpf;
}

