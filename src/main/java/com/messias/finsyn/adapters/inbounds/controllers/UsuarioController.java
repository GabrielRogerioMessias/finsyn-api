package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.application.usecases.UsuarioUseCases;
import com.messias.finsyn.domain.models.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    private final UsuarioUseCases usuarioCadastroUseCase;

    public UsuarioController(UsuarioUseCases usuarioCadastroUseCase) {
        this.usuarioCadastroUseCase = usuarioCadastroUseCase;
    }

    @PostMapping
    public ResponseEntity<Usuario> usuarioCadastroUseCase(@RequestBody UsuarioRegistrarDTO usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCadastroUseCase.cadastrar(usuario));
    }
}
