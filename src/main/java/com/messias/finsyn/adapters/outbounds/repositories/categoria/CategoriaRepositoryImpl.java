package com.messias.finsyn.adapters.outbounds.repositories.categoria;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.ports.out.CategoriaRepository;

import java.util.List;

public class CategoriaRepositoryImpl implements CategoriaRepository {
    private final JpaCategoriaRepository jpaCategoriaRepository;

    public CategoriaRepositoryImpl(JpaCategoriaRepository jpaCategoriaRepository) {
        this.jpaCategoriaRepository = jpaCategoriaRepository;
    }

    @Override
    public Categoria registrar(Categoria categoria) {
        return null;
    }

    @Override
    public void deletar(Long idcategoria) {

    }

    @Override
    public List<Categoria> buscarTodas() {
        return List.of();
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoria, Long idCategoria) {
        return null;
    }

    @Override
    public Categoria buscarCategoriaId(Long id) {
        return null;
    }
}
