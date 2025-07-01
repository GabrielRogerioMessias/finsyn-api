package com.messias.finsyn.adapters.outbounds.entities.enums;

import java.util.Arrays;

public enum TipoUsuario {
    COMUM("C", "Comum");

    private String codigo;
    private String descricao;

    TipoUsuario(String codigo, String descricao) {
        this.codigo = String.valueOf(codigo);
        this.descricao = descricao;
    }

    public static com.messias.finsyn.domain.models.enums.TipoUsuario valor(String codigo) {
        return Arrays.stream(com.messias.finsyn.domain.models.enums.TipoUsuario.values())
                .filter(context -> context.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public String getCodigo() {
        return codigo;
    }
}
