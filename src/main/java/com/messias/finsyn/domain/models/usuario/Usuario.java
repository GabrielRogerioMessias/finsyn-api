package com.messias.finsyn.domain.models.usuario;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import com.messias.finsyn.domain.models.transacao.Transacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private BigDecimal saldo;
    private List<Transacao> transacoes = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private List<MetaFinanceira> metasFinanceiras = new ArrayList<>();
    private List<DepositoMeta> depositosMetas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(UUID id, String nome, String sobrenome, String email,BigDecimal saldo, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<MetaFinanceira> getMetasFinanceiras() {
        return metasFinanceiras;
    }

    public void setMetasFinanceiras(List<MetaFinanceira> metasFinanceiras) {
        this.metasFinanceiras = metasFinanceiras;
    }

    public List<DepositoMeta> getDepositosMetas() {
        return depositosMetas;
    }

    public void setDepositosMetas(List<DepositoMeta> depositosMetas) {
        this.depositosMetas = depositosMetas;
    }
}
