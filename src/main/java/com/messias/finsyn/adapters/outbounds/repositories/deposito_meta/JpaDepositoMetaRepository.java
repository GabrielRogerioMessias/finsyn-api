package com.messias.finsyn.adapters.outbounds.repositories.deposito_meta;

import com.messias.finsyn.adapters.outbounds.entities.JpaDepositoMetaEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface JpaDepositoMetaRepository extends JpaRepository<JpaDepositoMetaEntity, Long> {


    @Query(value = "SELECT dp FROM JpaDepositoMetaEntity AS dp WHERE dp.usuario = :usuario AND dp.id=:idDeposito")
    Optional<JpaDepositoMetaEntity> buscarPorId(@Param(value = "usuario") JpaUsuarioEntity usuario,
                                                @Param(value = "idDeposito") Long idDeposito);

    @Query(value = "SELECT dp FROM JpaDepositoMetaEntity AS dp WHERE dp.usuario = :usuario AND dp.metaFinanceira.id =:idMeta ")
    Optional<JpaDepositoMetaEntity> buscarDepositosMeta(@Param(value = "usuario") JpaUsuarioEntity usuario,
                                                        @Param(value = "idMeta") Long idMeta);

    @Modifying
    @Transactional
    @Query("DELETE FROM JpaDepositoMetaEntity dp WHERE dp.id = :id")
    void deleteById(@Param("id") Long id);

    @Query(value = "SELECT dp FROM JpaDepositoMetaEntity as dp WHERE dp.id = :id")
    JpaDepositoMetaEntity byId(@Param("id") Long id);

}
