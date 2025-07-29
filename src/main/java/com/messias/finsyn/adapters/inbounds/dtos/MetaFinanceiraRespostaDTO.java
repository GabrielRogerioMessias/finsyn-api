package com.messias.finsyn.adapters.inbounds.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "DTO de Meta Financeira utilizado para respostas de buscar todas, e buscar por id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaFinanceiraRespostaDTO {
    @Schema(description = "ID da meta financeira", example = "1")
    private Long id;

    @Schema(description = "Descricção da Meta Financeira", example = "Viagem ao País X")
    private String descricao;

    @Schema(description = "Valor objetivo da Meta Financeira", example = "10.000")
    private BigDecimal valorObjetivo;

    @Schema(description = "Valor atual que o usuário possúi para tal meta financeira", example = "0.00")
    private Double valorAtual;

    @Schema(description = "Situação da meta financeira", example = "true")
    private Boolean ativo;

    @Schema(description = "Data em que o usuário já deseja ter terminado determinada meta", example = "2026-05-10")
    private Date dataLimite;
}
