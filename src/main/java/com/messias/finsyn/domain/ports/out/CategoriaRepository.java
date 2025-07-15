package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    Categoria registrar(Categoria categoria);

    void deletar(Categoria categoria);

    List<Categoria> buscarTodas(Usuario usuario);

    Categoria atualizarCategoria(Categoria categoria);

    Optional<Categoria> buscarCategoriaId(Usuario usuario, Long id);
}
