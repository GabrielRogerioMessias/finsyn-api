package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaTransacaoEntity;
import com.messias.finsyn.domain.models.transacao.Transacao;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface TransacaoMapper {
    @Mapping(target = "categoria.transacoes", ignore = true)
    @Mapping(target = "categoria.usuario", ignore = true)
    Transacao jpaToDomain(JpaTransacaoEntity jpaTransacaoEntity);

    @Mapping(target = "categoria.transacoes", ignore = true)
    @Mapping(target = "categoria.usuario", ignore = true)
    JpaTransacaoEntity domainToJpa(Transacao transacao);
}
