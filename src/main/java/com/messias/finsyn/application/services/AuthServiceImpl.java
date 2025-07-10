package com.messias.finsyn.application.services;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.application.exceptions.UsuarioJaRegistradoException;
import com.messias.finsyn.application.exceptions.UsuarioNaoEncontradoException;
import com.messias.finsyn.application.usecases.AuthUseCases;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import com.messias.finsyn.infrastructure.security.Token;
import com.messias.finsyn.infrastructure.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthUseCases {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;


    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Token login(LoginDataDTO login) {
        Usuario usuario = usuarioRepository.buscarPorEmail(login.getEmail()).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado com e-mail: " + login.getEmail()));
        if (passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
            return this.tokenService.generateToken(usuario);
        }
        return null;
    }

    @Override
    public Usuario registrar(Usuario novoUsuario) {
        if (usuarioRepository.buscarPorEmail(novoUsuario.getEmail()).isEmpty()) {
            novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
            return usuarioRepository.cadastrar(novoUsuario);
        } else {
            throw new UsuarioJaRegistradoException(novoUsuario.getEmail());
        }
    }
}
