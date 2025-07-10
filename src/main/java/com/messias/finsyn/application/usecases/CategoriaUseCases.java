package com.messias.finsyn.application.usecases;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface CategoriaUseCases {
    public Categoria criarNovaCategoria(Categoria categoria);

    public void deletarCategoria(Long idcategoria);

    public List<Categoria> buscarTodas();

    public Categoria atualizarCategoria(Categoria categoria, Long idCategoria);

    public Optional<Categoria> buscarCategoriaPorId(Long id);
}
