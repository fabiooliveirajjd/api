package com.fabio.api.dto.mapper;

import com.fabio.api.dto.ClienteCreateDto;
import com.fabio.api.dto.ClienteResponseDto;
import com.fabio.api.entity.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto dto) {
        return  new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDto toDto(Cliente cliente) {
        return  new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}
