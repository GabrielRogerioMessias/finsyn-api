package com.messias.finsyn.domain.models.meta_financeira;

import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MetaFinanceira {
    private Long id;
    private String descricao;
    private BigDecimal valorObjetivo;
    private BigDecimal valorAtual;
    private Boolean ativo;
    private Date dataLimite;
    private Usuario usuario;
    private List<DepositoMeta> depositos;

    public MetaFinanceira() {
    }

    public MetaFinanceira(Long id, String descricao, BigDecimal valorObjetivo, BigDecimal valorAtual, Boolean ativo, Date dataLimite, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.valorObjetivo = valorObjetivo;
        this.valorAtual = valorAtual;
        this.ativo = ativo;
        this.dataLimite = dataLimite;
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

    public BigDecimal getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(BigDecimal valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public BigDecimal getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(BigDecimal valorAtual) {
        this.valorAtual = valorAtual;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DepositoMeta> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<DepositoMeta> depositos) {
        this.depositos = depositos;
    }
}
