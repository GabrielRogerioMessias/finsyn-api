package com.messias.finsyn.adapters.outbounds.repositories.usuario;

import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, UUID> {
    Optional<JpaUsuarioEntity> findByEmail(String email);

}
