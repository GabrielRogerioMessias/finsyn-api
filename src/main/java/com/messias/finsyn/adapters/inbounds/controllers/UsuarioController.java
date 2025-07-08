package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.application.usecases.UsuarioUseCases;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioUseCases usuarioCadastroUseCase;

    public UsuarioController(UsuarioUseCases usuarioCadastroUseCase) {
        this.usuarioCadastroUseCase = usuarioCadastroUseCase;
    }

}
