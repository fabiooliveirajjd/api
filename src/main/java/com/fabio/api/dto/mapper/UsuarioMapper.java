/**
 * Mapeamento de Dados para a Entidade de Usuário.
 *
 * Esta classe fornece métodos para mapear dados entre objetos DTO (Data Transfer Objects) e a entidade de usuário.
 * Ela utiliza a biblioteca ModelMapper para realizar o mapeamento de dados de forma eficiente.
 */
package com.fabio.api.dto.mapper;

import com.fabio.api.dto.UsuarioCreateDto;
import com.fabio.api.dto.UsuarioResponseDto;
import com.fabio.api.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    /**
     * Converte um objeto DTO de criação de usuário em uma entidade de usuário.
     *
     * @param createDto O objeto DTO com os dados de criação de usuário.
     * @return Uma instância da entidade de usuário preenchida com os dados do DTO.
     */
    public static Usuario toUsuario(UsuarioCreateDto createDto){
        return new ModelMapper().map(createDto, Usuario.class);
    }

    /**
     * Converte uma entidade de usuário em um objeto DTO de resposta de usuário.
     *
     * @param usuario A entidade de usuário a ser convertida.
     * @return Um objeto DTO de resposta de usuário preenchido com os dados da entidade.
     */
    public static UsuarioResponseDto toDto(Usuario usuario){
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper =  new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    /**
     * Converte uma lista de entidades de usuário em uma lista de objetos DTO de resposta de usuário.
     *
     * @param usuarios A lista de entidades de usuário a ser convertida.
     * @return Uma lista de objetos DTO de resposta de usuário preenchida com os dados das entidades.
     */
    public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios){
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
