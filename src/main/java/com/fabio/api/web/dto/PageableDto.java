package com.fabio.api.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * ESSA CLASSE É USADA PARA RETORNAR OS DADOS DE PAGINAÇÃO.
 */
@Data
public class PageableDto {
    private List content = new ArrayList<>(); //LISTA DE DADOS
    private boolean first;//SE É A PRIMEIRA PÁGINA
    private boolean last;//SE É A ÚLTIMA PÁGINA
    @JsonProperty("page")//NOME DO ATRIBUTO QUE VEM NA REQUISIÇÃO
    private int number;//NÚMERO DA PÁGINA
    private int size;//QUANTIDADE DE ELEMENTOS POR PÁGINA
    @JsonProperty("pageElements")//NOME DO ATRIBUTO QUE VEM NA REQUISIÇÃO
    private int numberOfElements;//QUANTIDADE DE ELEMENTOS NA PÁGINA
    private int totalPages;//QUANTIDADE TOTAL DE PÁGINAS
    private int totalElements;//QUANTIDADE TOTAL DE ELEMENTOS
}

