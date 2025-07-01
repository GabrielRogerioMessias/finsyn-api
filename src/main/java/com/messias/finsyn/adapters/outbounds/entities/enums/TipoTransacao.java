package com.messias.finsyn.adapters.outbounds.entities.enums;

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

    public static com.messias.finsyn.domain.models.enums.TipoTransacao valor(String codigo) {
        return Arrays.stream(com.messias.finsyn.domain.models.enums.TipoTransacao.values())
                .filter(contexto -> contexto.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public String getCodigo() {
        return codigo;
    }
}
