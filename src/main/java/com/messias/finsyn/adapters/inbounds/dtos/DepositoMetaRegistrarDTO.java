package com.messias.finsyn.adapters.inbounds.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO utilizado para registrar/atualizar depósitos em metas financeiras")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositoMetaRegistrarDTO {
    @Schema(description = "Data em que o depósito foi realizado", example = "2025-07-15T22:50:00")
    @NotNull(message = "Data de deposito é obrigatória")
    private LocalDateTime dataDeposito;

    @Schema(description = "Valor do depósito", example = "50.00")
    @Min(value = 0, message = "O valor deve ser positivo")
    @NotNull(message = "O valor não pode ser nulo")
    private BigDecimal valor;

    @Schema(description = "Observação do depósito", example = "Observação do depósito")
    @NotBlank(message = "Observação é obrigatória")
    private String observacao;

    @Schema(description = "ID da meta financeira que receberá o depósito", example = "10")
    @NotNull(message = "ID da meta financeira é obrigatório")
    private Long idMetaFinanceira;
}
