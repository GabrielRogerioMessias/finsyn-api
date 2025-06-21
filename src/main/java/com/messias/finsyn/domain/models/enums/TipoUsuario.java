package com.messias.finsyn.domain.models.enums;

import java.util.Arrays;

public enum TipoUsuario {
    COMUM("C", "Comum");

    private String codigo;
    private String descricao;

    TipoUsuario(String codigo, String descricao) {
        this.codigo = String.valueOf(codigo);
        this.descricao = descricao;
    }

    public static TipoUsuario valor(String codigo) {
        return Arrays.stream(TipoUsuario.values())
                .filter(context -> context.codigo.equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public String getCodigo() {
        return codigo;
    }
}
