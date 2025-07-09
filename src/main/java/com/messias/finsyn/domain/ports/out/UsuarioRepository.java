package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    public Usuario cadastrar(Usuario usuario);

    public Optional<Usuario> buscarPorEmail(String email);
}
