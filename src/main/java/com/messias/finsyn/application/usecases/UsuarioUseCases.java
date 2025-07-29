package com.messias.finsyn.application.usecases;

import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.UUID;

public interface UsuarioUseCases {

    Usuario buscarPorId(UUID uuid);

    Usuario atualizar(Usuario usuarioAtualizado);
}
