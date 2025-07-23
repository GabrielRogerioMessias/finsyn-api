package com.messias.finsyn.adapters.inbounds.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "DTO utilizado para registrar/atualizar uma Meta Financeira")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaFinanceiraRegistrarDTO {
    @Schema(description = "Descrição da Meta Financeira", example = "Comprar Carro Modelo X")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Schema(description = "Valor objetivo da meta financeira", example = "10.000")
    @NotNull(message = "Valor objetivo é obrigatório")
    @Min(value = 0, message = "Valor objetivo deve ser maior que 0")
    private BigDecimal valorObjetivo;

    @Schema(description = "Valor atual que o usuário possui da meta financeira", example = "0.00")
    @Min(value = 0, message = "Valor objetivo deve ser maior que 0")
    private BigDecimal valorAtual;

    @Schema(description = "Data em que o usuário deseja ter completado a meta financeira", example = "2026-05-10")
    @NotNull(message = "Data limite é obrigatória")
    @Future(message = "Datalimite deve estar no futuro")
    private Date dataLimite;
}
