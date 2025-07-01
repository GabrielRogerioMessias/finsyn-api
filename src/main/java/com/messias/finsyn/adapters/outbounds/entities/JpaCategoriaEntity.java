package com.messias.finsyn.adapters.outbounds.entities;

import com.messias.finsyn.adapters.outbounds.entities.enums.TipoTransacao;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categoria")
@Data
public class JpaCategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private JpaUsuarioEntity usuario;
    @OneToMany(mappedBy = "categoria")
    private List<JpaTransacaoEntity> transacoes;
}
