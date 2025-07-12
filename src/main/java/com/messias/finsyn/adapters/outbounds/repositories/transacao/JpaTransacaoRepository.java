package com.messias.finsyn.adapters.outbounds.repositories.transacao;

import com.messias.finsyn.adapters.outbounds.entities.JpaTransacaoEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaTransacaoRepository extends JpaRepository<JpaTransacaoEntity, Long> {
    @Query("SELECT t FROM JpaTransacaoEntity AS t where t.usuario = :usuario")
    List<JpaTransacaoEntity> buscarTransacoesUsuario(@Param(value = "usuario") JpaUsuarioEntity usuario);

    @Query("SELECT t FROM JpaTransacaoEntity AS t WHERE t.usuario = :usuario AND t.id = :transacaoId")
    Optional<JpaTransacaoEntity> buscarPorIdEUsuario(@Param(value = "usuario") JpaUsuarioEntity usuario,
                                                     @Param(value = "transacaoId") Long transacaoId);
}
