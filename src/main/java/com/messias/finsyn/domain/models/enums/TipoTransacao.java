package com.messias.finsyn.domain.models.enums;

import java.util.Arrays;

public enum TipoTransacao {
    RECEITA("R", "Receita"),
    DESPESA("D", "Despesa"),
    TRANSFERENCIA_META("TM", "Transferência Meta");

    private String codigo;
    private String descricao;

    TipoTransacao(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static TipoTransacao valor(String codigo) {
        return Arrays.stream(TipoTransacao.values())
                .filter(contexto -> contexto.codigo.equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public String getCodigo() {
        return codigo;
    }
}
