package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.usuario.Usuario;

public interface UsuarioRepository {
    public Usuario cadastrar(Usuario usuario);

    public Usuario buscarPorEmail(String email);
}
