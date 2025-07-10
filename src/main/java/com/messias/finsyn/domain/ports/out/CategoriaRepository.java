package com.messias.finsyn.domain.ports.out;

import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    public Categoria registrar(Categoria categoria);

    public void deletar(Usuario usuario, Long idcategoria);

    public List<Categoria> buscarTodas(Usuario usuario);

    public Categoria atualizarCategoria(Categoria categoria);

    public Optional<Categoria> buscarCategoriaId(Usuario usuario, Long id);
}
