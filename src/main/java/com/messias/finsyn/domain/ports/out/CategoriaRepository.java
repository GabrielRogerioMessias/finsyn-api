package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.categoria.Categoria;

import java.util.List;

public interface CategoriaRepository {
    public Categoria registrar(Categoria categoria);

    public void deletar(Long idcategoria);

    public List<Categoria> buscarTodas();

    public Categoria atualizarCategoria(Categoria categoria, Long idCategoria);

    public Categoria buscarCategoriaId(Long id);
}
