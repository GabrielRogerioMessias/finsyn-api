package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaCategoriaEntity;
import com.messias.finsyn.domain.models.categoria.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    JpaCategoriaEntity domainToJpaCategoria(Categoria categoria);

    Categoria jpaCategoriaToDomain(JpaCategoriaEntity jpaCategoria);
}
