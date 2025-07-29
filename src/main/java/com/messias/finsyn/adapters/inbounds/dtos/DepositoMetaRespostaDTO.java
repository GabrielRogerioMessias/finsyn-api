package com.messias.finsyn.adapters.inbounds.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO de Deposito Meta utilizado para respostas de operações buscar todos, e buscar por id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositoMetaRespostaDTO {
    @Schema(description = "ID do depósito meta", example = "10")
    private Long id;

    @Schema(description = "Data do depósito", example = "2025-07-15T22:50:00")
    private LocalDateTime dataDeposito;

    @Schema(description = "Valor do depósito", example = "50.00")
    private BigDecimal valor;

    @Schema(description = "Observação do depósito", example = "Dinheiro da venda x")
    private String observacao;

    @Schema(description = "Contém a descrição da meta financeira em que o depósito foi realizado", example = "Comprar Carro x")
    private String descricaoMetaFinanceira;

    @Schema(description = "Contém a descrição da transação", example = "R$:0.00 transferido para: Comprar Carro x")
    private String descricaoTransacao;
}
