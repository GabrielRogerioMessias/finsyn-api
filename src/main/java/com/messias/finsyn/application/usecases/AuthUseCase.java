package com.messias.finsyn.application.usecases;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRespostaDTO;
import com.messias.finsyn.infrastructure.security.Token;

public interface AuthUseCase {
    public Token login(LoginDataDTO login);

    public UsuarioRespostaDTO registrar(UsuarioRegistrarDTO registrar);
}
