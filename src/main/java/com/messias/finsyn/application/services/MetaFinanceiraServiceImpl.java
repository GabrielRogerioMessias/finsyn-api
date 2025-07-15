package com.messias.finsyn.application.services;

import com.messias.finsyn.application.exceptions.EntidadeNaoEncontradaException;
import com.messias.finsyn.application.usecases.MetaFinanceiraUseCases;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.MetaFinanceiraRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MetaFinanceiraServiceImpl implements MetaFinanceiraUseCases {
    private final MetaFinanceiraRepository repository;
    private final SecurityUtils securityUtils;

    public MetaFinanceiraServiceImpl(MetaFinanceiraRepository metaFinanceiraRepository, SecurityUtils securityUtils) {
        this.repository = metaFinanceiraRepository;
        this.securityUtils = securityUtils;
    }

    @Override
    public MetaFinanceira criarNovaMeta(MetaFinanceira metaFinanceira) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        metaFinanceira.setUsuario(usuario);
        metaFinanceira.setAtivo(true);
        if (metaFinanceira.getValorAtual() == null) metaFinanceira.setValorAtual(BigDecimal.ZERO);
        return repository.criarMeta(metaFinanceira);
    }

    @Override
    public void deletarMeta(Long idMeta) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        MetaFinanceira meta = repository.buscarPorId(usuario, idMeta).orElseThrow(() -> new EntidadeNaoEncontradaException("Meta Financeira não encontrada com o id: " + idMeta));
        repository.deletarMeta(meta);
    }

    @Override
    public List<MetaFinanceira> buscarTodas() {
        Usuario usuario = securityUtils.usuarioAutenticado();
        return repository.buscarTodas(usuario);
    }

    @Override
    public MetaFinanceira atualizarMeta(MetaFinanceira metaAtualizada, Long idExistente) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        MetaFinanceira existente = repository.buscarPorId(usuario, idExistente).orElseThrow(() -> new EntidadeNaoEncontradaException("Meta Financeira não encontrada com o id: " + idExistente));
        this.atualizarCampos(existente, metaAtualizada);
        return repository.atualizarMeta(existente);
    }

    @Override
    public MetaFinanceira buscarPorId(Long idMeta) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        return repository.buscarPorId(usuario, idMeta).orElseThrow(() -> new EntidadeNaoEncontradaException("Meta Financeira não encontrada com o id: " + idMeta));
    }

    private void atualizarCampos(MetaFinanceira existente, MetaFinanceira atualizada) {
        existente.setValorAtual(atualizada.getValorAtual());
        existente.setValorObjetivo(atualizada.getValorObjetivo());
        existente.setDescricao(atualizada.getDescricao());
        existente.setDataLimite(atualizada.getDataLimite());
    }
}
