package com.messias.finsyn.adapters.outbounds.repositories;

import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.UsuarioCadastroUseCase;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioCadastroUseCase {

    @Override
    public void cadastrar(Usuario usuario) {

    }
}
