package com.messias.finsyn.application.usecases;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.infrastructure.security.Token;

public interface AuthUseCases {
    public Token login(LoginDataDTO login);

    public Usuario registrar(Usuario registrar);
}
