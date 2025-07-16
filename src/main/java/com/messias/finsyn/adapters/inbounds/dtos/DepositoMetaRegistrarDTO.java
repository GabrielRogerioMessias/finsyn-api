package com.messias.finsyn.adapters.inbounds.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositoMetaRegistrarDTO {
    @NotNull(message = "Data de deposito é obrigatória")
    private LocalDateTime dataDeposito;
    @Min(value = 0, message = "O valor deve ser positivo")
    @NotNull(message = "O valor não pode ser nulo")
    private BigDecimal valor;
    @NotBlank(message = "Observação é obrigatória")
    private String observacao;
    @NotNull(message = "ID da meta financeira é obrigatório")
    private Long idMetaFinanceira;
}
