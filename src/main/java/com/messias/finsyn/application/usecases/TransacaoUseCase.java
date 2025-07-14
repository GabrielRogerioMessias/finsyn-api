package com.messias.finsyn.application.usecases;

import com.messias.finsyn.domain.models.transacao.Transacao;

import java.util.List;

public interface TransacaoUseCase {
    Transacao registrarTransacao(Transacao transacao);

    void excluirTransacao(Long idTransacao);

    List<Transacao> buscarTodas();

    Transacao buscarPorId(Long idTransacao);

    Transacao atualizar(Transacao transacaoAtualizada, Long idTransacaoExistente);
}
