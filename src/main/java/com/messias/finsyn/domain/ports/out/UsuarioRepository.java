package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Usuario cadastrar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorId(UUID uuid);

    Usuario atualizar(Usuario usuario);


}
