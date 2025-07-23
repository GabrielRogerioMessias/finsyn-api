package com.messias.finsyn.adapters.inbounds.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "DTO de resposta que representa os dados públicos de um usuário")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRespostaDTO {
    @Schema(description = "Identificador único do usuário", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Primeiro nome do usuário", example = "Exemplo")
    private String nome;

    @Schema(description = "Sobrenome do usuário", example = "Jonildson")
    private String sobrenome;

    @Schema(description = "Endereço de e-mail do usuário", example = "exemplo.unico@email.com")
    private String email;
    
    @Schema(description = "Saldo atual da conta do usuário", example = "1500.00")
    private BigDecimal saldo;
}
