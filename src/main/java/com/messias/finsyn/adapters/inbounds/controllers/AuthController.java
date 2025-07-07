package com.messias.finsyn.adapters.inbounds.controllers;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.application.usecases.AuthUseCase;
import com.messias.finsyn.infrastructure.security.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody LoginDataDTO login) {
        Token token = authUseCase.login(login);
        return ResponseEntity.ok().body(token);
    }
}
