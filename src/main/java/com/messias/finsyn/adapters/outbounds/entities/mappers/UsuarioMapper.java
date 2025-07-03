package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.domain.models.usuario.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario jpaUsuarioToUsuario(Usuario usuario);

    JpaUsuarioEntity usuarioToJpaUsuario(Usuario usuario);
}
