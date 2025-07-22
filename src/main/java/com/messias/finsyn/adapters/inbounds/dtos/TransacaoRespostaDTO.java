package com.messias.finsyn.adapters.inbounds.dtos;

import com.messias.finsyn.domain.models.enums.TipoTransacao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO de resposta que representa uma transação financeira registrada pelo usuário")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRespostaDTO {
    @Schema(description = "ID da transação", example = "102")
    private Long id;

    @Schema(description = "Descrição da transação", example = "Pagamento de energia elétrica")
    private String descricao;

    @Schema(description = "Valor monetário da transação", example = "250.75")
    private BigDecimal valor;

    @Schema(description = "Tipo da transação: RECEITA ou DESPESA", example = "DESPESA")
    private TipoTransacao tipo;

    @Schema(description = "Data e hora em que a transação foi realizada", example = "2025-07-22T14:30:00")
    private LocalDateTime dataTransacao;

    @Schema(description = "Descrição da categoria associada à transação", example = "Despesas Casa")
    private String descricaoCategoria;
}
