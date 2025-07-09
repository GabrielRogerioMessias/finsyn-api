package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.UsuarioDTOMapper;
import com.messias.finsyn.application.usecases.AuthUseCase;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.infrastructure.security.Token;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUseCase authUseCase;
    private final UsuarioDTOMapper usuarioDTOMapper;

    public AuthController(AuthUseCase authUseCase, UsuarioDTOMapper usuarioDTOMapper) {
        this.authUseCase = authUseCase;
        this.usuarioDTOMapper = usuarioDTOMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid LoginDataDTO login) {
        Token token = authUseCase.login(login);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioRespostaDTO> register(@RequestBody UsuarioRegistrarDTO registrarDTO) {
        Usuario usuario = usuarioDTOMapper.dtoRegistrarToDomain(registrarDTO);
        return ResponseEntity.ok().body(usuarioDTOMapper.dtoRespostaToDto(authUseCase.registrar(usuario)));
    }
}
