package com.messias.finsyn.adapters.outbounds.entities;

import com.messias.finsyn.adapters.outbounds.entities.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Data
public class JpaUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    @OneToMany(mappedBy = "usuario")
    private List<JpaCategoriaEntity> categorias;
    @OneToMany(mappedBy = "usuario")
    private List<JpaMetaFinanceiraEntity> metasFinanceiras;
    @OneToMany(mappedBy = "usuario")
    private List<JpaTransacaoEntity> transacoes;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private TipoUsuario tipo;
    @OneToMany(mappedBy = "usuario")
    private List<JpaDepositoMetaEntity> depositosMetas;
}
