package com.messias.finsyn.application.usecases;

import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;


import java.util.List;


public interface DepositoMetaUseCases {
    DepositoMeta registrar(DepositoMeta depositoMeta);

    void deletar(Long idDepositoMeta);

    List<DepositoMeta> buscarTodos();

    DepositoMeta atualizar(DepositoMeta depositoMetaAtualizado, Long idDepositoExistente);

    DepositoMeta buscarPorId(Long idDeposito);
}
