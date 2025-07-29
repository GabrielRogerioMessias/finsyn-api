package com.messias.finsyn.adapters.outbounds.entities.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

public enum TipoTransacao {
    RECEITA("R", "Receita"),
    DESPESA("D", "Despesa"),
    TRANSFERENCIA_META("TM", "Transferência Meta");

    private final String codigo;
    @Getter
    private final String descricao;

    TipoTransacao(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @JsonCreator
    public static TipoTransacao valor(String codigo) {
        return Arrays.stream(TipoTransacao.values())
                .filter(contexto -> contexto.codigo.equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Code: " + codigo));
    }

    @JsonValue
    public String getCodigo() {
        return codigo;
    }
}
