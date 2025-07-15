package com.messias.finsyn.application.usecases;

import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.domain.models.usuario.Usuario;

public interface UsuarioUseCases {
    Usuario cadastrar(UsuarioRegistrarDTO usuario);
}
