package com.messias.finsyn.application.services;

import com.messias.finsyn.application.exceptions.EntidadeNaoEncontradaException;
import com.messias.finsyn.application.usecases.TransacaoUseCase;
import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.enums.TipoTransacao;
import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.CategoriaRepository;
import com.messias.finsyn.domain.ports.out.TransacaoRepository;
import com.messias.finsyn.domain.ports.out.UsuarioRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class TransacaoServiceImpl implements TransacaoUseCase {
    private final TransacaoRepository repository;
    private final SecurityUtils securityUtils;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public TransacaoServiceImpl(TransacaoRepository repository,
                                SecurityUtils securityUtils,
                                CategoriaRepository categoriaRepository,
                                UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.securityUtils = securityUtils;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void excluirTransacao(Long idTransacao) {
        Usuario usuarioAutenticado = securityUtils.usuarioAutenticado();
        Transacao transacao = repository.buscarPorId(usuarioAutenticado, idTransacao).orElseThrow(() -> new EntidadeNaoEncontradaException("Transação não encontrada com o id: " + idTransacao));
        BigDecimal valorOperacao = this.modificarSaldo(transacao);
        if (transacao.getTipo().equals(TipoTransacao.RECEITA)) {
            usuarioAutenticado.setSaldo(usuarioAutenticado.getSaldo().subtract(valorOperacao));
        } else if (transacao.getTipo().equals(TipoTransacao.DESPESA)) {
            BigDecimal valor = BigDecimal.valueOf(valorOperacao.doubleValue() * -1);
            usuarioAutenticado.setSaldo(usuarioAutenticado.getSaldo().add(valor));
        }
        usuarioRepository.cadastrar(usuarioAutenticado);
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
        //Salvando dados necessários para retornar ao estado inicial dos saldo (antes das DESPESA/RECEITA)
        BigDecimal valorAntigo = existente.getValor();
        TipoTransacao tipoAntigo = existente.getTipo();
        BigDecimal valorAtualizado = transacaoAtualizada.getValor();
        TipoTransacao tipoAtualizado = transacaoAtualizada.getTipo();
        //Atualizando campos
        this.atualizarCampos(existente, transacaoAtualizada);
        //Voltando os campos para o valor inicial
        if (tipoAntigo == TipoTransacao.RECEITA) {
            usuarioAutenticado.setSaldo(usuarioAutenticado.getSaldo().subtract(valorAntigo));
        } else if (tipoAntigo == TipoTransacao.DESPESA) {
            usuarioAutenticado.setSaldo(usuarioAutenticado.getSaldo().add(valorAntigo));
        }
        //Atualizando os valores dos campos com novos valores
        if (tipoAtualizado == TipoTransacao.RECEITA) {
            usuarioAutenticado.setSaldo(usuarioAutenticado.getSaldo().add(valorAtualizado));
        } else if (tipoAtualizado == TipoTransacao.DESPESA) {
            usuarioAutenticado.setSaldo(usuarioAutenticado.getSaldo().subtract(valorAtualizado));
        }
        Categoria categoria = categoriaRepository.buscarCategoriaId(usuarioAutenticado, transacaoAtualizada.getCategoria().getId()).orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com o id: " + transacaoAtualizada.getCategoria().getId()));
        existente.setCategoria(categoria);
        //Salvando alterações do saldo do usuário
        usuarioRepository.cadastrar(usuarioAutenticado);
        return repository.atualizarTransacao(existente);
    }

    @Override
    public Transacao registrarTransacao(Transacao transacao) {
        Usuario usuarioAutenticado = securityUtils.usuarioAutenticado();
        Long idCategoria = transacao.getCategoria().getId();
        Categoria categoria = categoriaRepository.buscarCategoriaId(usuarioAutenticado, idCategoria)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com o id: " + idCategoria));
        BigDecimal valorOperacao = this.modificarSaldo(transacao);
        usuarioAutenticado.setSaldo(usuarioAutenticado.getSaldo().add(valorOperacao));
        transacao.setUsuario(usuarioAutenticado);
        transacao.setCategoria(categoria);
        usuarioRepository.cadastrar(usuarioAutenticado);
        return repository.criarTransacao(transacao);
    }

    private void atualizarCampos(Transacao existente, Transacao atualizada) {
        existente.setDataTransacao(atualizada.getDataTransacao());
        existente.setTipo(atualizada.getTipo());
        existente.setValor(atualizada.getValor());
        existente.setDescricao(atualizada.getDescricao());
    }

    private BigDecimal modificarSaldo(Transacao transacao) {
        if (transacao.getTipo().equals(TipoTransacao.DESPESA)) {
            return BigDecimal.valueOf(transacao.getValor().doubleValue() * -1);
        } else if (transacao.getTipo().equals(TipoTransacao.RECEITA)) {
            return transacao.getValor();
        }
        return null;
    }
}
