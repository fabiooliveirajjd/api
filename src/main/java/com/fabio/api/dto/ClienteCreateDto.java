package com.fabio.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateDto {

        @NotBlank
        @Size(min = 5, max = 100)
        private String nome;

        @NotBlank
        @Size(min = 11, max = 11)
        @CPF
        private String cpf;

}
