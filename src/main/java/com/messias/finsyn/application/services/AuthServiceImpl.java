package com.messias.finsyn.application.services;

import com.messias.finsyn.adapters.inbounds.dtos.LoginDataDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRespostaDTO;
import com.messias.finsyn.adapters.inbounds.mappers.UsuarioDTOMapper;
import com.messias.finsyn.application.usecases.AuthUseCase;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import com.messias.finsyn.infrastructure.security.Token;
import com.messias.finsyn.infrastructure.security.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UsuarioDTOMapper usuarioMapper;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService, UsuarioDTOMapper usuarioMapper) {
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Token login(LoginDataDTO login) {
        Usuario usuario = usuarioRepository.buscarPorEmail(login.getEmail());
        if (passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
            return this.tokenService.generateToken(usuario);
        }
        return null;
    }

    @Override
    public UsuarioRespostaDTO registrar(UsuarioRegistrarDTO registrar) {
        Usuario usuario = usuarioMapper.dtoRegistrarToDomain(registrar);
        usuario.setSenha(passwordEncoder.encode(registrar.getSenha()));
        return usuarioMapper.dtoRespostaToDto(usuarioRepository.cadastrar(usuario));
    }
}
