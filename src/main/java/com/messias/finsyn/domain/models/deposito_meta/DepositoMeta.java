package com.messias.finsyn.domain.models.deposito_meta;

import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositoMeta {
    private Long id;
    private LocalDateTime dataDeposito;
    private BigDecimal valor;
    private String observacao;
    private MetaFinanceira metaFinanceira;
    private Transacao transacao;
    private Usuario usuario;

    public DepositoMeta() {
    }

    public DepositoMeta(Long id, LocalDateTime dataDeposito, BigDecimal valor, String observacao, MetaFinanceira metaFinanceira, Transacao transacao, Usuario usuario) {
        this.id = id;
        this.dataDeposito = dataDeposito;
        this.valor = valor;
        this.observacao = observacao;
        this.metaFinanceira = metaFinanceira;
        this.transacao = transacao;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(LocalDateTime dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public MetaFinanceira getMetaFinanceira() {
        return metaFinanceira;
    }

    public void setMetaFinanceira(MetaFinanceira metaFinanceira) {
        this.metaFinanceira = metaFinanceira;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
