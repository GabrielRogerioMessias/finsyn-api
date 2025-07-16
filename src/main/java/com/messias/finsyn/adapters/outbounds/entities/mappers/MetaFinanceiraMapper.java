package com.messias.finsyn.adapters.outbounds.entities.mappers;

import com.messias.finsyn.adapters.outbounds.entities.JpaMetaFinanceiraEntity;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepositoMetaMapper.class, UsuarioMapper.class})
public interface MetaFinanceiraMapper {

    @Mapping(target = "usuario.categorias", ignore = true)
    @Mapping(target = "usuario.metasFinanceiras", ignore = true)
    @Mapping(target = "usuario.depositosMetas", ignore = true)
    @Mapping(target = "usuario.transacoes", ignore = true)
    JpaMetaFinanceiraEntity domainToJpa(MetaFinanceira metaFinanceira);

    @Mapping(target = "usuario.categorias", ignore = true)
    @Mapping(target = "usuario.metasFinanceiras", ignore = true)
    @Mapping(target = "usuario.depositosMetas", ignore = true)
    @Mapping(target = "usuario.transacoes", ignore = true)
    MetaFinanceira jpaToDomain(JpaMetaFinanceiraEntity metaFinanceiraEntity);
}
