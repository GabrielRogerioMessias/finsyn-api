package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaCategoriaEntity;
import com.messias.finsyn.domain.models.categoria.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, TransacaoMapper.class})
public interface CategoriaMapper {
    @Mapping(target = "usuario.categorias", ignore = true)
    JpaCategoriaEntity domainToJpaCategoria(Categoria categoria);
    @Mapping(target = "usuario.categorias", ignore = true)
    Categoria jpaCategoriaToDomain(JpaCategoriaEntity jpaCategoria);
}
