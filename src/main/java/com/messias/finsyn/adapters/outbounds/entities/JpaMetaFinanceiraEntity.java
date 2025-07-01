package com.messias.finsyn.adapters.outbounds.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meta_financeira")
@Data
public class JpaMetaFinanceiraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Column(name = "valor_objetivo")
    private BigDecimal valorObjetivo;
    @Column(name = "valor_atual")
    private BigDecimal valorAtual;
    @Column(name = "ativa")
    private Boolean ativo;
    @Column(name = "data_limite")
    private Date dataLimite;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private JpaUsuarioEntity usuario;
    @OneToMany(mappedBy = "metaFinanceira")
    private List<JpaDepositoMetaEntity> depositos;
}
