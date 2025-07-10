package com.messias.finsyn.application.services;

import com.messias.finsyn.application.usecases.CategoriaUseCases;
import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.CategoriaRepository;

import java.util.List;
import java.util.Optional;

public class CategoriaServiceImpl implements CategoriaUseCases {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria criarNovaCategoria(Categoria categoria) {
        return null;
    }

    @Override
    public void deletarCategoria(Usuario usuario, Long idcategoria) {

    }

    @Override
    public List<Categoria> buscarTodas(Usuario usuario) {
        return List.of();
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoria, Long idCategoria) {
        return null;
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Usuario usuario, Long id) {
        return Optional.empty();
    }
}
