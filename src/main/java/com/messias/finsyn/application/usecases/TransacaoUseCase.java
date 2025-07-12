package com.messias.finsyn.application.usecases;

import com.messias.finsyn.domain.models.transacao.Transacao;

import java.util.List;

public interface TransacaoUseCase {
    public Transacao registrarTransacao(Transacao transacao);

    public void excluirTransacao(Long idTransacao);

    public List<Transacao> buscarTodas();

    public Transacao buscarPorId(Long idTransacao);

    public Transacao atualizar(Transacao transacaoAtualizada, Long idTransacaoExistente);
}
