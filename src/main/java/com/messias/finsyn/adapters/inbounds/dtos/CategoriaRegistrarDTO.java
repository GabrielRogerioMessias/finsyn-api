package com.messias.finsyn.adapters.inbounds.dtos;

import com.messias.finsyn.adapters.outbounds.entities.enums.TipoTransacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRegistrarDTO {
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    @NotNull(message = "O Tipo não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;
}
