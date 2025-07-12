package com.messias.finsyn.application.services;

import com.messias.finsyn.application.exceptions.EntidadeNaoEncontradaException;
import com.messias.finsyn.application.usecases.TransacaoUseCase;
import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.CategoriaRepository;
import com.messias.finsyn.domain.ports.out.TransacaoRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TransacaoServiceImpl implements TransacaoUseCase {
    private final TransacaoRepository repository;
    private final SecurityUtils securityUtils;
    private final CategoriaRepository categoriaRepository;

    public TransacaoServiceImpl(TransacaoRepository repository,
                                SecurityUtils securityUtils,
                                CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.securityUtils = securityUtils;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void excluirTransacao(Long idTransacao) {
        Usuario usuarioAutenticado = securityUtils.usuarioAutenticado();
        Transacao transacao = repository.buscarPorId(usuarioAutenticado, idTransacao).orElseThrow(() -> new EntidadeNaoEncontradaException("Transação não encontrada com o id: " + idTransacao));
        repository.deletarTransacao(transacao);
    }

    @Override
    public List<Transacao> buscarTodas() {
        Usuario usuarioAutenticado = securityUtils.usuarioAutenticado();
        return repository.buscarTodas(usuarioAutenticado);
    }

    @Override
    public Transacao buscarPorId(Long idTransacao) {
        Usuario usuarioAutenticado = securityUtils.usuarioAutenticado();
        return repository.buscarPorId(usuarioAutenticado, idTransacao).orElseThrow(() -> new EntidadeNaoEncontradaException("Transação não encontrada com o id: " + idTransacao));

    }

    @Override
    public Transacao atualizar(Transacao transacaoAtualizada, Long idTransacaoExistente) {
        Usuario usuarioAutenticado = securityUtils.usuarioAutenticado();
        Transacao existente = repository.buscarPorId(usuarioAutenticado, idTransacaoExistente).orElseThrow(() -> new EntidadeNaoEncontradaException("Transação não encontrada com o id: " + idTransacaoExistente));
        this.atualizarCampos(existente, transacaoAtualizada);
        if (transacaoAtualizada.getCategoria().getId() == null) {
            return repository.atualizarTransacao(existente);
        }
        Optional<Categoria> categoria = categoriaRepository.buscarCategoriaId(usuarioAutenticado, transacaoAtualizada.getCategoria().getId()).or(Optional::empty);
        if (categoria.isEmpty()) {
            return repository.atualizarTransacao(existente);
        }
        existente.setCategoria(categoria.get());
        return repository.atualizarTransacao(existente);
    }

    @Override
    public Transacao registrarTransacao(Transacao transacao) {
        Usuario usuarioAutenticado = securityUtils.usuarioAutenticado();
        Long idCategoria = transacao.getCategoria().getId();
        Categoria categoria = categoriaRepository.buscarCategoriaId(usuarioAutenticado, idCategoria).orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com o id: " + idCategoria));
        transacao.setUsuario(usuarioAutenticado);
        transacao.setCategoria(categoria);
        return repository.criarTransacao(transacao);
    }

    private void atualizarCampos(Transacao existente, Transacao atualizada) {
        existente.setDataTransacao(atualizada.getDataTransacao());
        existente.setTipo(atualizada.getTipo());
        existente.setValor(atualizada.getValor());
        existente.setDescricao(atualizada.getDescricao());
    }
}
