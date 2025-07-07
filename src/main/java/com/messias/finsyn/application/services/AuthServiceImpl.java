package com.messias.finsyn.application.services;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.entities.mappers.UsuarioMapper;
import com.messias.finsyn.adapters.outbounds.repositories.JpaUsuarioRepository;
import com.messias.finsyn.application.usecases.AuthUseCase;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.infrastructure.security.Token;
import com.messias.finsyn.infrastructure.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthUseCase {
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UsuarioMapper usuarioMapper;

    public AuthServiceImpl(JpaUsuarioRepository jpaUsuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService, UsuarioMapper usuarioMapper) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Token login(LoginDataDTO login) {
        JpaUsuarioEntity jpaUsuarioEntity = jpaUsuarioRepository.findByEmail(login.getEmail()).orElseThrow(() -> new RuntimeException("usuário não encontrado"));
        Usuario usuario = usuarioMapper.jpaUsuarioToDominio(jpaUsuarioEntity);
        if (passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
            return this.tokenService.generateToken(usuario);
        }
        return null;
    }
}
