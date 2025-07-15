package com.messias.finsyn.adapters.outbounds.repositories.deposito_meta;

import com.messias.finsyn.adapters.outbounds.entities.JpaDepositoMetaEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaDepositoMetaRepository extends JpaRepository<JpaDepositoMetaEntity, Long> {
    @Query(value = "SELECT dp FROM JpaDepositoMetaEntity AS dp WHERE dp.usuario = :usuario")
    List<JpaDepositoMetaEntity> buscarTodos(@Param(value = "usuario") JpaUsuarioEntity usuario);

    @Query(value = "SELECT dp FROM JpaDepositoMetaEntity AS dp WHERE dp.usuario = :usuario AND dp.id=:idDeposito")
    Optional<JpaDepositoMetaEntity> buscarPorId(@Param(value = "usuario") JpaUsuarioEntity usuario,
                                                @Param(value = "idDeposito") Long idDeposito);
}
