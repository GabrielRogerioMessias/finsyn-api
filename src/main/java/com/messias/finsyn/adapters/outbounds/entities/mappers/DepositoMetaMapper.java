package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaDepositoMetaEntity;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface DepositoMetaMapper {
    @Mapping(target = "transacao.usuario", ignore = true)
    @Mapping(target = "transacao.categoria", ignore = true)
    @Mapping(target = "metaFinanceira.usuario", ignore = true)
    @Mapping(target = "metaFinanceira.depositos", ignore = true)
    DepositoMeta toDomain(JpaDepositoMetaEntity jpaEntity);
    
    @Mapping(target = "transacao.usuario", ignore = true)
    @Mapping(target = "transacao.categoria", ignore = true)
    @Mapping(target = "metaFinanceira.usuario", ignore = true)
    @Mapping(target = "metaFinanceira.depositos", ignore = true)
    JpaDepositoMetaEntity domainToJpa(DepositoMeta depositoMeta);
}
