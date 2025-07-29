package com.messias.finsyn.application.usecases;

import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;

import java.util.List;

public interface MetaFinanceiraUseCases {
    MetaFinanceira criarNovaMeta(MetaFinanceira metaFinanceira);

    void deletarMeta(Long idMeta);

    List<MetaFinanceira> buscarTodas();

    MetaFinanceira atualizarMeta(MetaFinanceira metaAtualizada, Long idExistente);

    MetaFinanceira buscarPorId(Long idMeta);
}
