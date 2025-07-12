package com.messias.finsyn.adapters.outbounds.repositories.transacao;

import com.messias.finsyn.adapters.outbounds.entities.JpaTransacaoEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.entities.mappers.TransacaoMapper;
import com.messias.finsyn.adapters.outbounds.entities.mappers.UsuarioMapper;
import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.TransacaoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransacaoRepositoryImpl implements TransacaoRepository {
    private final JpaTransacaoRepository jpaTransacaoRepository;
    private final TransacaoMapper mapper;
    private final UsuarioMapper usuarioMapper;

    public TransacaoRepositoryImpl(JpaTransacaoRepository jpaTransacaoRepository,
                                   TransacaoMapper transacaoMapper,
                                   UsuarioMapper usuarioMapper) {
        this.jpaTransacaoRepository = jpaTransacaoRepository;
        this.mapper = transacaoMapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Transacao criarTransacao(Transacao novaTransacao) {
        JpaTransacaoEntity jpaTransacao = mapper.domainToJpa(novaTransacao);
        return mapper.jpaToDomain(jpaTransacaoRepository.save(jpaTransacao));
    }

    @Override
    public void deletarTransacao(Transacao transacao) {
        JpaTransacaoEntity jpaTransacao = mapper.domainToJpa(transacao);
        jpaTransacaoRepository.delete(jpaTransacao);
    }

    @Override
    public List<Transacao> buscarTodas(Usuario usuario) {
        JpaUsuarioEntity usuarioEntity = usuarioMapper.usuarioToJpaUsuario(usuario);
        return jpaTransacaoRepository.buscarTransacoesUsuario(usuarioEntity)
                .stream().map(mapper::jpaToDomain)
                .toList();
    }

    @Override
    public Transacao atualizarTransacao(Transacao transacao) {
        JpaTransacaoEntity entity = mapper.domainToJpa(transacao);
        return mapper.jpaToDomain(jpaTransacaoRepository.save(entity));
    }

    @Override
    public Optional<Transacao> buscarPorId(Usuario usuario, Long id) {
        JpaUsuarioEntity usuarioEntity = usuarioMapper.usuarioToJpaUsuario(usuario);
        Optional<JpaTransacaoEntity> entity = jpaTransacaoRepository.buscarPorIdEUsuario(usuarioEntity, id);
        return entity.map(mapper::jpaToDomain);
    }
}
