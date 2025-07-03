package com.messias.finsyn.adapters.inbounds.mappers;

import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.domain.models.usuario.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {
    UsuarioRegistrarDTO domainToDtoRegistrar(Usuario usuario);

    Usuario dtoRegistrarToDomain(UsuarioRegistrarDTO usuario);
}
