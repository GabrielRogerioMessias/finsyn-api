package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface MetasFinanceirasRepository {
    MetaFinanceira criarMeta(MetaFinanceira metaFinanceira);

    void deletarMeta(MetaFinanceira metaFinanceira);

    List<MetaFinanceira> buscarTodas(Usuario usuario);

    MetaFinanceira atualizarMeta(MetaFinanceira metaFinanceira);

    Optional<MetaFinanceira> buscarPorId(Long idMeta);
}
