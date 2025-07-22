package com.messias.finsyn.adapters.inbounds.dtos;

import com.messias.finsyn.adapters.outbounds.entities.enums.TipoTransacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "DTO utilizado para registrar/atualizar Transações que podem ser do tipo R -> RECEITA e D -> DESPESA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRegistrarDTO {
    @Schema(description = "Descição da transação", example = "Pagamento faculdade X")
    @NotBlank(message = "Descricão é obrigatória")
    private String descricao;

    @Schema(description = "Valor da Transação", example = "700.00")
    @NotNull(message = "Valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser positivo")
    private BigDecimal valor;

    @Schema(description = "Tipo da transação", example = "R para RECEITA e D para DESPESA")
    @NotNull(message = "O tipo não pode ser nulo")
    private TipoTransacao tipo;

    @Schema(description = "Data da DESPESA/RECEITA", example = "2025-07-20T19:14:00")
    @NotNull(message = "Data da transação não pode ser nula")
    private LocalDateTime dataTransacao;

    @Schema(description = "ID da categoria da transação", example = "1 -> FACULDADE")
    @NotNull(message = "Id da Categoria não pode ser nula")
    private Long idCategoria;
}
