package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.domain.models.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "transacoes", ignore = true)
    @Mapping(target = "metasFinanceiras", ignore = true)
    @Mapping(target = "depositosMetas", ignore = true)
    Usuario jpaUsuarioToDominio(JpaUsuarioEntity jpaUsuarioEntity);

    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "transacoes", ignore = true)
    @Mapping(target = "metasFinanceiras", ignore = true)
    @Mapping(target = "depositosMetas", ignore = true)
    JpaUsuarioEntity usuarioToJpaUsuario(Usuario usuario);
}
