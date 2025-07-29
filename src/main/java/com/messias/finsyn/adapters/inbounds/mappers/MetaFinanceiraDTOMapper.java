package com.messias.finsyn.adapters.inbounds.mappers;


import com.messias.finsyn.adapters.inbounds.dtos.MetaFinanceiraRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.MetaFinanceiraRespostaDTO;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MetaFinanceiraDTOMapper {
    @Mapping(source = "depositos", target = "valorAtual", qualifiedByName = "somarDepositos")
    MetaFinanceiraRespostaDTO domainToRespostaDTO(MetaFinanceira metaFinanceira);

    MetaFinanceira metaRegistrarDtoToDomain(MetaFinanceiraRegistrarDTO dto);

    @Named("somarDepositos")
    static Double somarDepositos(List<DepositoMeta> depositoMetas) {
        if (depositoMetas == null) return 0.00;
        return depositoMetas.stream()
                .map(DepositoMeta::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
    }
}
