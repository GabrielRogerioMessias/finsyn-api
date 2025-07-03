package com.messias.finsyn.adapters.outbounds.repositories;

import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.entities.mappers.UsuarioMapper;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final UsuarioMapper usuarioMapper;
    private final JpaUsuarioRepository jpaUsuarioRepository;

    public UsuarioRepositoryImpl(JpaUsuarioRepository jpaUsuarioRepository, UsuarioMapper usuarioMapper) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        JpaUsuarioEntity entidade = usuarioMapper.usuarioToJpaUsuario(usuario);
        JpaUsuarioEntity jpaEntity = jpaUsuarioRepository.save(entidade);
        return usuarioMapper.jpaUsuarioToDominio(jpaEntity);
    }
}
