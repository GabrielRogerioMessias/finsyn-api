package com.messias.finsyn.adapters.outbounds.repositories.categoria;

import com.messias.finsyn.adapters.outbounds.entities.JpaCategoriaEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface JpaCategoriaRepository extends JpaRepository<JpaCategoriaEntity, Long> {
    @Query("SELECT c FROM JpaCategoriaEntity AS c WHERE c.usuario = :usuario")
    List<JpaCategoriaEntity> buscarCategoriaPorIdEUsuario(@Param(value = "usuario") JpaUsuarioEntity usuario);

    @Query("SELECT c FROM JpaCategoriaEntity AS c WHERE c.usuario = :usuario AND c.id = :categoriaID")
    Optional<JpaCategoriaEntity> buscarPorIdEUsuario(@Param(value = "usuario") JpaUsuarioEntity usuario,
                                                     @Param(value = "categoriaID") Long categoriaID);

}
