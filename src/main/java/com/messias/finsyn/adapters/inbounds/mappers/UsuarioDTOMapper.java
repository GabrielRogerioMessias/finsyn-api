package com.messias.finsyn.adapters.inbounds.mappers;

import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRespostaDTO;
import com.messias.finsyn.domain.models.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {
    @Mapping(target = "categorias", ignore = true)
    Usuario usuarioRegistrarDtoToDomain(UsuarioRegistrarDTO usuario);
    UsuarioRespostaDTO domainToUsuarioRespostaDTO(Usuario usuario);


}
