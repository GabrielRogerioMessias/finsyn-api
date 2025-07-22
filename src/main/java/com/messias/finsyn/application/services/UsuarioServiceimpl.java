package com.messias.finsyn.application.services;

import com.messias.finsyn.application.exceptions.EntidadeNaoEncontradaException;
import com.messias.finsyn.application.usecases.UsuarioUseCases;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioServiceimpl implements UsuarioUseCases {
    private final UsuarioRepository usuarioRepository;
    private final SecurityUtils securityUtils;

    public UsuarioServiceimpl(UsuarioRepository usuarioRepository,
                              SecurityUtils securityUtils) {
        this.usuarioRepository = usuarioRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public Usuario buscarPorId(UUID uuid) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        Usuario resultado = usuarioRepository.buscarPorId(uuid).orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o ID: " + uuid));
        if (usuario.getId() != resultado.getId()) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado com o ID: " + uuid);
        }
        return resultado;
    }

    @Override
    public Usuario atualizar(Usuario usuarioAtualizado) {
        Usuario existente = securityUtils.usuarioAutenticado();
        this.atualizarCampos(existente, usuarioAtualizado);
        System.out.println(existente.getNome() + existente.getId());
        return usuarioRepository.atualizar(existente);
    }

    private void atualizarCampos(Usuario existente, Usuario atualizado) {
        existente.setSobrenome(atualizado.getSobrenome());
        existente.setNome(atualizado.getNome());
        existente.setSaldo(atualizado.getSaldo());
    }
}
