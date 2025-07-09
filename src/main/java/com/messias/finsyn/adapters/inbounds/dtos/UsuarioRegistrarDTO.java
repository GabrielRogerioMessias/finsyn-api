package com.messias.finsyn.adapters.inbounds.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistrarDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Sobrenome é obrigatório")
    private String sobrenome;
    @NotBlank(message = "Nome é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    @Size(max = 20, min = 6, message = "A senha deve conter no mínimo 4 caracteres e máximo de 20")
    private String senha;
}
