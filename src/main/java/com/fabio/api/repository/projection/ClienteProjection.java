package com.fabio.api.repository.projection;

//ESSA INTERFACE É USADA PARA PROJEÇÃO DE DADOS, QUE PODE SUBSTITUIR O DTO.
public interface ClienteProjection {

    Long getId();
    String getNome();
    String getCpf();
}
