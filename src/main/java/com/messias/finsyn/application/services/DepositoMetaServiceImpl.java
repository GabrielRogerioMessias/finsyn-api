package com.messias.finsyn.application.services;

import com.messias.finsyn.application.exceptions.EntidadeNaoEncontradaException;
import com.messias.finsyn.application.usecases.DepositoMetaUseCases;
import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import com.messias.finsyn.domain.models.enums.TipoTransacao;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.DepositoMetaRepository;
import com.messias.finsyn.domain.ports.out.MetaFinanceiraRepository;
import com.messias.finsyn.domain.ports.out.TransacaoRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositoMetaServiceImpl implements DepositoMetaUseCases {
    private final DepositoMetaRepository repository;
    private final SecurityUtils securityUtils;
    private final MetaFinanceiraRepository metaFinanceiraRepository;
    private final TransacaoRepository transacaoRepository;

    public DepositoMetaServiceImpl(DepositoMetaRepository repository,
                                   SecurityUtils securityUtils,
                                   MetaFinanceiraRepository metaFinanceiraRepository,
                                   TransacaoRepository transacaoRepository) {
        this.repository = repository;
        this.securityUtils = securityUtils;
        this.metaFinanceiraRepository = metaFinanceiraRepository;
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    public DepositoMeta registrar(DepositoMeta depositoMeta) {
        Long idMetaFinanceira = depositoMeta.getMetaFinanceira().getId();
        Usuario usuario = securityUtils.usuarioAutenticado();
        MetaFinanceira metaFinanceira = metaFinanceiraRepository.buscarPorId(usuario, idMetaFinanceira).orElseThrow(() -> new EntidadeNaoEncontradaException("Meta financeira não encontrada com o ID: " + idMetaFinanceira));

        Transacao transacao = new Transacao();
        transacao.setUsuario(usuario);
        transacao.setDescricao(depositoMeta.getValor() + " transferido para: " + metaFinanceira.getDescricao());
        transacao.setTipo(TipoTransacao.TRANSFERENCIA_META);
        transacao.setDataTransacao(depositoMeta.getDataDeposito());
        transacao.setValor(depositoMeta.getValor());
        transacao = transacaoRepository.criarTransacao(transacao);

        depositoMeta.setMetaFinanceira(metaFinanceira);
        depositoMeta.setTransacao(transacao);
        depositoMeta.setUsuario(usuario);
        return repository.registrar(depositoMeta);
    }

    @Override
    public void deletar(Long idDepositoMeta) {

    }

    @Override
    public List<DepositoMeta> buscarTodos() {
        return List.of();
    }

    @Override
    public DepositoMeta atualizar(DepositoMeta depositoMetaAtualizado, Long idDepositoExistente) {
        return null;
    }

    @Override
    public DepositoMeta buscarPorId(Long idDeposito) {
        return null;
    }
}
