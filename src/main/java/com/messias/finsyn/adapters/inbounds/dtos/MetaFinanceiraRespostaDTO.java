package com.messias.finsyn.adapters.inbounds.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaFinanceiraRespostaDTO {
    private Long id;
    private String descricao;
    private BigDecimal valorObjetivo;
    private Double valorAtual;
    private Boolean ativo;
    private Date dataLimite;
}
