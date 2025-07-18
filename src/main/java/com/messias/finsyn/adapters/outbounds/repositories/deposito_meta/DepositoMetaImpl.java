package com.messias.finsyn.adapters.outbounds.repositories.deposito_meta;

import com.messias.finsyn.adapters.outbounds.entities.JpaDepositoMetaEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaTransacaoEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.entities.mappers.DepositoMetaMapper;
import com.messias.finsyn.adapters.outbounds.entities.mappers.UsuarioMapper;
import com.messias.finsyn.adapters.outbounds.repositories.transacao.JpaTransacaoRepository;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.DepositoMetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class DepositoMetaImpl implements DepositoMetaRepository {
    private final JpaDepositoMetaRepository repository;
    private final DepositoMetaMapper mapper;
    private final UsuarioMapper usuarioMapper;
    private final JpaTransacaoRepository transacaoRepository;

    public DepositoMetaImpl(JpaDepositoMetaRepository repository,
                            DepositoMetaMapper mapper,
                            UsuarioMapper usuarioMapper,
                            JpaTransacaoRepository transacaoRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioMapper = usuarioMapper;
        this.transacaoRepository = transacaoRepository;
    }


    @Override
    public DepositoMeta registrar(DepositoMeta depositoMeta) {
        JpaDepositoMetaEntity jpaEntity = mapper.domainToJpa(depositoMeta);
        return mapper.toDomain(repository.save(jpaEntity));
    }

    @Transactional
    @Override
    public void deletar(DepositoMeta depositoMeta) {
        JpaDepositoMetaEntity entity = repository.byId(depositoMeta.getId());
        JpaTransacaoEntity transacaoEntity = entity.getTransacao();
        repository.delete(entity);
        transacaoRepository.delete(transacaoEntity);
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
    public List<DepositoMeta> buscarTodos(Usuario usuario, Long idMeta) {
        JpaUsuarioEntity jpaUsuario = usuarioMapper.usuarioToJpaUsuario(usuario);
        return repository.buscarDepositosMeta(jpaUsuario, idMeta)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
