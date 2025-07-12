package com.messias.finsyn.adapters.inbounds.dtos;

import com.messias.finsyn.domain.models.enums.TipoTransacao;
import com.messias.finsyn.domain.models.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRespostaDTO {
    private Long id;
    private String descricao;
    private TipoTransacao tipo;
}
