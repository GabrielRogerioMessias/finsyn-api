package com.messias.finsyn.adapters.inbounds.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRegistrarDTO {
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
}
