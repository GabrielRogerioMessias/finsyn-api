package com.messias.finsyn.domain.models.categoria;

import com.messias.finsyn.domain.models.enums.TipoTransacao;
import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;

public class Categoria {
    private Long id;
    private String descricao;
    private TipoTransacao tipo;
    private Usuario usuario;
    private List<Transacao> transacoes;

    public Categoria() {
    }

    public Categoria(Long id, String descricao, TipoTransacao tipo, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
