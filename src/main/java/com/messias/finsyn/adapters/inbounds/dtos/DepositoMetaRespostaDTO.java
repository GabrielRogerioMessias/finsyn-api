package com.messias.finsyn.adapters.inbounds.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositoMetaRespostaDTO {
    private Long id;
    private LocalDateTime dataDeposito;
    private BigDecimal valor;
    private String observacao;
    private String descricaoMetaFinanceira;
    private String descricaoTransacao;
}
