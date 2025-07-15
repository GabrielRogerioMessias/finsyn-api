package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Usuario cadastrar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);
}
