package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.transacao.Transacao;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository {
    public Transacao criarTransacao(Transacao transacao);

    public void deletarTransacao(Transacao transacao);

    public List<Transacao> buscarTodas(Usuario usuario);

    public Transacao atualizarTransacao(Transacao transacao);

    public Optional<Transacao> buscarPorId(Usuario usuario, Long id);

}
