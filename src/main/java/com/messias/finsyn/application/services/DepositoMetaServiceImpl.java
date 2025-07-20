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
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DepositoMetaServiceImpl implements DepositoMetaUseCases {
    private final DepositoMetaRepository repository;
    private final SecurityUtils securityUtils;
    private final MetaFinanceiraRepository metaFinanceiraRepository;
    private final TransacaoRepository transacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public DepositoMetaServiceImpl(DepositoMetaRepository repository,
                                   SecurityUtils securityUtils,
                                   MetaFinanceiraRepository metaFinanceiraRepository,
                                   TransacaoRepository transacaoRepository,
                                   UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.securityUtils = securityUtils;
        this.metaFinanceiraRepository = metaFinanceiraRepository;
        this.transacaoRepository = transacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public DepositoMeta registrar(DepositoMeta depositoMeta) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        Long idMetaFinanceira = depositoMeta.getMetaFinanceira().getId();
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
        usuario.setSaldo(usuario.getSaldo().subtract(transacao.getValor()));
        usuarioRepository.cadastrar(usuario);
        return repository.registrar(depositoMeta);
    }

    @Override
    public void deletar(Long idDepositoMeta) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        DepositoMeta depositoMeta = repository.buscarPorId(usuario, idDepositoMeta)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Deposito não encontrado com o id: " + idDepositoMeta));
        usuario.setSaldo(usuario.getSaldo().add(depositoMeta.getValor()));
        usuarioRepository.cadastrar(usuario);
        repository.deletar(depositoMeta);
    }


    @Override
    public List<DepositoMeta> buscarTodos(Long idMetaFinanceira) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        return repository.buscarTodos(usuario, idMetaFinanceira);
    }

    @Override
    public DepositoMeta atualizar(DepositoMeta depositoMetaAtualizado, Long idDepositoExistente) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        DepositoMeta existente = repository.buscarPorId(usuario, idDepositoExistente).orElseThrow(() -> new EntidadeNaoEncontradaException("Deposito não encontrado com o id: " + idDepositoExistente));
        BigDecimal valorAntigo = existente.getValor();
        BigDecimal valorAtualizado = depositoMetaAtualizado.getValor();
        //Voltando para o valor anterior
        usuario.setSaldo(usuario.getSaldo().add(valorAntigo));
        atualizarCampos(existente, depositoMetaAtualizado);
        //Atualizando saldo para novo valor
        usuario.setSaldo(usuario.getSaldo().subtract(valorAtualizado));
        usuarioRepository.cadastrar(usuario);
        return repository.atualizar(existente);
    }

    @Override
    public DepositoMeta buscarPorId(Long idDeposito) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        return repository.buscarPorId(usuario, idDeposito).orElseThrow(() -> new EntidadeNaoEncontradaException("Deposito não encontrado com o id: " + idDeposito));
    }

    private void atualizarCampos(DepositoMeta existente, DepositoMeta atualizado) {
        existente.setValor(atualizado.getValor());
        existente.setObservacao(atualizado.getObservacao());
        existente.setDataDeposito(atualizado.getDataDeposito());
    }
}
