package com.messias.finsyn.adapters.inbounds.dtos;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaFinanceiraRegistrarDTO {
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    @NotNull(message = "Valor objetivo é obrigatório")
    @Min(value = 0, message = "Valor objetivo deve ser maior que 0")
    private BigDecimal valorObjetivo;
    @Min(value = 0, message = "Valor objetivo deve ser maior que 0")
    private BigDecimal valorAtual;
    @NotNull(message = "Data limite é obrigatória")
    @Future(message = "Datalimite deve estar no futuro")
    private Date dataLimite;
}
