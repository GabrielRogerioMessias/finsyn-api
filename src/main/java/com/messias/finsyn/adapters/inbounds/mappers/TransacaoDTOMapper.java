package com.messias.finsyn.adapters.inbounds.mappers;

import com.messias.finsyn.adapters.inbounds.dtos.TransacaoRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.TransacaoRespostaDTO;
import com.messias.finsyn.domain.models.transacao.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransacaoDTOMapper {
    @Mapping(source = "idCategoria", target = "categoria.id")
    Transacao transacaoRegistrarDtoToDomain(TransacaoRegistrarDTO transacaoRegistrarDTO);
    
    @Mapping(source = "categoria.descricao", target = "descricaoCategoria")
    TransacaoRespostaDTO domainToTransacaoRespostaDTO(Transacao transacao);
}
