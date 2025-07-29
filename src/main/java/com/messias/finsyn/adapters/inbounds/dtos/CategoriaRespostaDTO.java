package com.messias.finsyn.adapters.inbounds.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO de Categoria utilizado para respostas de buscar todas, e buscar por id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRespostaDTO {
    @Schema(description = "ID da categoria", example = "1")
    private Long id;

    @Schema(description = "Descrição da categoria", example = "Despesas")
    private String descricao;
}
