package com.messias.finsyn.application.usecases;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.infrastructure.security.Token;

public interface AuthUseCases {
    Token login(LoginDataDTO login);

    Usuario registrar(Usuario registrar);
}
