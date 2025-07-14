package com.messias.finsyn.adapters.outbounds.repositories.meta_financeira;

import com.messias.finsyn.adapters.outbounds.entities.JpaMetaFinanceiraEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.domain.models.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaMetaFinanceiraRepository extends JpaRepository<JpaMetaFinanceiraEntity, Long> {
    @Query(value = "SELECT mt FROM JpaMetaFinanceiraEntity AS mt WHERE mt.usuario =:usuario")
    List<JpaMetaFinanceiraEntity> buscarTodas(@Param(value = "usuario") JpaUsuarioEntity usuario);

    @Query(value = "SELECT mt FROM JpaMetaFinanceiraEntity AS mt WHERE mt.usuario=:usuario AND mt.id = :idMetaFinanceira")
    Optional<JpaMetaFinanceiraEntity> buscarPorId(@Param(value = "usuario") JpaUsuarioEntity usuario,
                                                  @Param(value = "idMetaFinanceira") Long idMetaFinanceira);

    Long id(Long id);
}
