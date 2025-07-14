package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository {
    Transacao criarTransacao(Transacao transacao);

    void deletarTransacao(Transacao transacao);

    List<Transacao> buscarTodas(Usuario usuario);

    Transacao atualizarTransacao(Transacao transacao);

    Optional<Transacao> buscarPorId(Usuario usuario, Long id);

}
