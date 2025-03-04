package br.com.springboot.condominiospazio.model;

import lombok.Getter;

@Getter
public enum Tipo {

    VISITANTE("Visitante"),
    MORADOR("Morador"),
    PROPRIETARIO("Proprietário"),
    PRESTADOR("Prestador"),
    PROPRIETARIO_MORADOR("Proprietario e Morador");

    private String descricao;

    Tipo(String descricao) {

        this.descricao = descricao;

    }

    public void setDescricao(String descricao){

        this.descricao = descricao;
    }

}
