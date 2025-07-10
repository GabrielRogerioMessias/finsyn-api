package com.messias.finsyn.adapters.inbounds.mappers;

import com.messias.finsyn.adapters.inbounds.dtos.CategoriaRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.CategoriaRespostaDTO;
import com.messias.finsyn.domain.models.categoria.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaDTOMapper {
    Categoria categoriaRegistrarDtoToDomain(CategoriaRegistrarDTO registrarDTO);

    CategoriaRespostaDTO domainToCategoriaRespostaDto(Categoria categoria);
}