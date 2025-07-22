package com.messias.finsyn.adapters.inbounds.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "DTO utilizado para registrar/atualizar um novo usuário no sistema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistrarDTO {
    @Schema(description = "Primeiro nome do usuário", example = "Exemplo")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Sobrenome do usuário", example = "Exemplo Exemplo")
    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;

    @Schema(description = "Endereço de e-mail válido e único", example = "exemplo.unico@email.com")
    @NotBlank(message = "Nome é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @Schema(description = "Senha do usuário com no mínimo 6 e no máximo 20 caracteres", example = "ExemploSenha123")
    @NotBlank(message = "A senha é obrigatória")
    @Size(max = 20, min = 6, message = "A senha deve conter no mínimo 4 caracteres e máximo de 20")
    private String senha;

    @Schema(description = "Saldo inicial da conta do usuário", example = "1000.00")
    @NotNull(message = "Saldo é obrigatório")
    private BigDecimal saldo;
}
