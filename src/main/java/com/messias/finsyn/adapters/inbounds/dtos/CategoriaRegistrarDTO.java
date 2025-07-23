package com.messias.finsyn.adapters.inbounds.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO utilizado para registrar/atualizar novas categorias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRegistrarDTO {
    @Schema(description = "Descrição da categoria, deve contém mais de 3 e menos de 30 caracteres", example = "despesas")
    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 3, max = 30, message = "A descrição deve ter entre 3 e 30 caracteres")
    private String descricao;
}
