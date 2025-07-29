package com.messias.finsyn.adapters.outbounds.entities;

import com.messias.finsyn.adapters.outbounds.entities.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACAO")
@Data
public class JpaTransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    @Column(name = "data_transacao")
    private LocalDateTime dataTransacao;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private JpaUsuarioEntity usuario;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private JpaCategoriaEntity categoria;
    @OneToOne(mappedBy = "transacao")
    private JpaDepositoMetaEntity deposito;
}
