package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.deposito_meta.DepositoMeta;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.Optional;

public interface DepositoMetaRepository {
    DepositoMeta registrar(DepositoMeta depositoMeta);

    void deletar(DepositoMeta depositoMeta);

    DepositoMeta atualizar(DepositoMeta depositoMeta);

    Optional<DepositoMeta> buscarPorId(Usuario usuario, Long idDeposito);
}
