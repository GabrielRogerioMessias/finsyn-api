package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaTransacaoEntity;
import com.messias.finsyn.domain.models.transacao.Transacao;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {
    @Mapping(target = "categoria.usuario.categorias", ignore = true)
    @Mapping(target = "usuario.categorias", ignore = true)
    @Mapping(target = "usuario.transacoes", ignore = true)
    Transacao jpaToDomain(JpaTransacaoEntity jpaTransacaoEntity);
    @Mapping(target = "categoria.usuario.categorias", ignore = true)
    @Mapping(target = "usuario.categorias", ignore = true)
    @Mapping(target = "usuario.transacoes", ignore = true)
    JpaTransacaoEntity domainToJpa(Transacao transacao);
}
