package com.messias.finsyn.adapters.inbounds.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRespostaDTO {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private BigDecimal saldo;
}
