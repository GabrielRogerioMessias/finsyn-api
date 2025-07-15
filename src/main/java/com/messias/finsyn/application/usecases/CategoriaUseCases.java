package com.messias.finsyn.application.usecases;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface CategoriaUseCases {
    Categoria criarNovaCategoria(Categoria categoria);

    void deletarCategoria(Long idcategoria);

    List<Categoria> buscarTodas();

    Categoria atualizarCategoria(Categoria categoria, Long idCategoria);

    Categoria buscarCategoriaPorId(Long id);
}
