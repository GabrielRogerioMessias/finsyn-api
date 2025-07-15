package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaDepositoMetaEntity;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepositoMetaMapper {
    DepositoMeta toDomain(JpaDepositoMetaEntity jpaEntity);

    JpaDepositoMetaEntity domainToJpa(DepositoMeta depositoMeta);
}
