package com.messias.finsyn.adapters.inbounds.dtos;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRespostaDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private LocalDateTime dataTransacao;
    private Categoria categoria;
}
