package com.messias.finsyn.adapters.inbounds.mappers;

import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.DepositoMetaRespostaDTO;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepositoMetaDTOMapper {
    @Mapping(source = "idMetaFinanceira", target = "metaFinanceira.id")
    DepositoMeta dtoRegistrarToDomain(DepositoMetaRegistrarDTO registrarDTO);

    @Mapping(source = "metaFinanceira.descricao", target = "descricaoMetaFinanceira")
    @Mapping(source = "transacao.descricao", target = "descricaoTransacao")
    DepositoMetaRespostaDTO domainToDtoResposta(DepositoMeta depositoMeta);
}