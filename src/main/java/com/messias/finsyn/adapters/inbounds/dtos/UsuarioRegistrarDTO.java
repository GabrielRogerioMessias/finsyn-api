package com.messias.finsyn.adapters.inbounds.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistrarDTO {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
}
