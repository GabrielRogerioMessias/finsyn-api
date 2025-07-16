package com.messias.finsyn.adapters.outbounds.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DEPOSITO_META")
@Data
public class JpaDepositoMetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_deposito")
    private LocalDateTime dataDeposito;
    private BigDecimal valor;
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "id_meta_financeira")
    private JpaMetaFinanceiraEntity metaFinanceira;
    @OneToOne(optional = false)
    @JoinColumn(name = "id_transacao")
    private JpaTransacaoEntity transacao;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private JpaUsuarioEntity usuario;

}
