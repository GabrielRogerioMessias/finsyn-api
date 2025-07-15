package com.messias.finsyn.adapters.outbounds.repositories.deposito_meta;

import com.messias.finsyn.adapters.outbounds.entities.JpaDepositoMetaEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.entities.mappers.DepositoMetaMapper;
import com.messias.finsyn.adapters.outbounds.entities.mappers.UsuarioMapper;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.DepositoMetaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepositoMetaImpl implements DepositoMetaRepository {
    private final JpaDepositoMetaRepository repository;
    private final DepositoMetaMapper mapper;
    private final UsuarioMapper usuarioMapper;


    public DepositoMetaImpl(JpaDepositoMetaRepository repository,
                            DepositoMetaMapper mapper,
                            UsuarioMapper usuarioMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public DepositoMeta registrar(DepositoMeta depositoMeta) {
        JpaDepositoMetaEntity jpaEntity = mapper.domainToJpa(depositoMeta);
        return mapper.toDomain(repository.save(jpaEntity));
    }

    @Override
    public void deletar(DepositoMeta depositoMeta) {
        repository.delete(mapper.domainToJpa(depositoMeta));
    }

    @Override
    public DepositoMeta atualizar(DepositoMeta depositoMeta) {
        JpaDepositoMetaEntity jpaEntity = mapper.domainToJpa(depositoMeta);
        return mapper.toDomain(repository.save(jpaEntity));
    }

    @Override
    public Optional<DepositoMeta> buscarPorId(Usuario usuario, Long idDeposito) {
        JpaUsuarioEntity jpaUsuario = usuarioMapper.usuarioToJpaUsuario(usuario);
        Optional<JpaDepositoMetaEntity> entity = repository.buscarPorId(jpaUsuario, idDeposito);
        return entity.map(mapper::toDomain);
    }

    @Override
    public List<DepositoMeta> buscarTodos(Usuario usuario) {
        JpaUsuarioEntity jpaUsuario = usuarioMapper.usuarioToJpaUsuario(usuario);
        return repository.buscarTodos(jpaUsuario)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
