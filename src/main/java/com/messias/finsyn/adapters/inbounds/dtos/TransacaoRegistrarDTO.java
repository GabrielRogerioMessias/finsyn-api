package com.messias.finsyn.adapters.inbounds.dtos;

import com.messias.finsyn.adapters.outbounds.entities.enums.TipoTransacao;
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
public class TransacaoRegistrarDTO {
    @NotBlank(message = "Descricão é obrigatória")
    private String descricao;
    @NotNull(message = "Valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser positivo")
    private BigDecimal valor;
    @NotNull(message = "O tipo não pode ser nulo")
    private TipoTransacao tipo;
    @NotNull(message = "Data da transação não pode ser nula")
    private LocalDateTime dataTransacao;
    @NotNull(message = "Id da Categoria não pode ser nula")
    private Long idCategoria;
}
