package com.messias.finsyn.adapters.inbounds.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UsuarioRegistrarDTO {
    private UUID id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

}
