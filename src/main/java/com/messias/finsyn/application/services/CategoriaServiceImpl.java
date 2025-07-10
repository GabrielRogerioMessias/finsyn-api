package com.messias.finsyn.application.services;

import com.messias.finsyn.application.usecases.CategoriaUseCases;
import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.CategoriaRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaUseCases {
    private final CategoriaRepository categoriaRepository;
    private final SecurityUtils securityUtils;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, SecurityUtils securityUtils) {
        this.categoriaRepository = categoriaRepository;
        this.securityUtils = securityUtils;
    }


    @Override
    public Categoria criarNovaCategoria(Categoria categoria) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        categoria.setUsuario(usuario);
        return categoriaRepository.registrar(categoria);
    }

    @Override
    public void deletarCategoria(Long idcategoria) {

    }

    @Override
    public List<Categoria> buscarTodas() {
        Usuario usuario = securityUtils.usuarioAutenticado();
        return categoriaRepository.buscarTodas(usuario);
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoria, Long idCategoria) {
        return null;
    }

    @Override
    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return Optional.empty();
    }
}
