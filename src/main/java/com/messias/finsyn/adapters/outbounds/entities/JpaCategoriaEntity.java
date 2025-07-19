package com.messias.finsyn.adapters.outbounds.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "CATEGORIA")
@Data
public class JpaCategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private JpaUsuarioEntity usuario;
    @OneToMany(mappedBy = "categoria")
    private List<JpaTransacaoEntity> transacoes;
}
