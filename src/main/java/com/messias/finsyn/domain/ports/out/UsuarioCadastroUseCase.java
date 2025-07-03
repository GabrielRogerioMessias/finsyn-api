package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.usuario.Usuario;

public interface UsuarioCadastroUseCase {
    public Usuario cadastrar(Usuario usuario);
}
