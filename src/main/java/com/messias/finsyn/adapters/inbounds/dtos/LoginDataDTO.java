package com.messias.finsyn.adapters.inbounds.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDataDTO {
    @Schema(description = "Email para efetuar o login", example = "exemplo@exemplo.com")
    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @Schema(description = "Senha para efetuar o login", example = "exemplo")
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
}
