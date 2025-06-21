package com.messias.finsyn.domain.models.transacao;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.enums.TipoTransacao;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private LocalDateTime dataTransacao;
    private Usuario usuario;
    private Categoria categoria;

    public Transacao() {
    }

    public Transacao(Long id, String descricao, BigDecimal valor, TipoTransacao tipo, LocalDateTime dataTransacao, Categoria categoria, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.dataTransacao = dataTransacao;
        this.categoria = categoria;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
