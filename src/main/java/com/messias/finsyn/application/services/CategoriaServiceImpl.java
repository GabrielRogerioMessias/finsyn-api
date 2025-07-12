package com.messias.finsyn.application.services;

import com.messias.finsyn.application.exceptions.EntidadeNaoEncontradaException;
import com.messias.finsyn.application.usecases.CategoriaUseCases;
import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.CategoriaRepository;
import com.messias.finsyn.infrastructure.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Usuario usuario = securityUtils.usuarioAutenticado();
        Categoria categoria = categoriaRepository.buscarCategoriaId(usuario, idcategoria).orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com o id: " + idcategoria));
        categoriaRepository.deletar(categoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        Usuario usuario = securityUtils.usuarioAutenticado();
        return categoriaRepository.buscarTodas(usuario);
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoriaAtualizada, Long idCategoria) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        Categoria existente = categoriaRepository.buscarCategoriaId(usuario, idCategoria).orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com o di: " + idCategoria));
        this.atualizarCampos(existente, categoriaAtualizada);
        return categoriaRepository.atualizarCategoria(existente);
    }

    @Override
    public Categoria buscarCategoriaPorId(Long idCategoria) {
        Usuario usuario = securityUtils.usuarioAutenticado();
        return categoriaRepository.buscarCategoriaId(usuario, idCategoria).orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria não encontrada com o di: " + idCategoria));
    }

    private void atualizarCampos(Categoria existente, Categoria atualizada) {
        existente.setDescricao(atualizada.getDescricao());
        existente.setTipo(atualizada.getTipo());
    }
}

