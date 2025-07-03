package com.messias.finsyn.application.services;

import com.messias.finsyn.adapters.inbounds.dtos.UsuarioRegistrarDTO;
import com.messias.finsyn.adapters.inbounds.mappers.UsuarioDTOMapper;
import com.messias.finsyn.application.usecases.UsuarioUseCases;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceimpl implements UsuarioUseCases {
    private final UsuarioRepository usuarioCadastroUseCase;
    private final UsuarioDTOMapper usuarioDTOMapper;

    public UsuarioServiceimpl(UsuarioRepository usuarioCadastroUseCase, UsuarioDTOMapper usuarioDTOMapper) {
        this.usuarioCadastroUseCase = usuarioCadastroUseCase;
        this.usuarioDTOMapper = usuarioDTOMapper;
    }

    @Override
    public Usuario cadastrar(UsuarioRegistrarDTO usuario) {
        Usuario novoUsuario = usuarioDTOMapper.dtoRegistrarToDomain(usuario);
        return usuarioCadastroUseCase.cadastrar(novoUsuario);
    }
}
