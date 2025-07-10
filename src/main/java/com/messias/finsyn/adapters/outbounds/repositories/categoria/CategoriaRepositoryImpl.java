package com.messias.finsyn.adapters.outbounds.repositories.categoria;

import com.messias.finsyn.adapters.outbounds.entities.JpaCategoriaEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.entities.mappers.CategoriaMapper;
import com.messias.finsyn.adapters.outbounds.entities.mappers.UsuarioMapper;
import com.messias.finsyn.domain.models.categoria.Categoria;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.CategoriaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {
    private final JpaCategoriaRepository jpaCategoriaRepository;
    private final CategoriaMapper categoriaMapper;
    private final UsuarioMapper usuarioMapper;

    public CategoriaRepositoryImpl(JpaCategoriaRepository jpaCategoriaRepository,
                                   CategoriaMapper categoriaMapper,
                                   UsuarioMapper usuarioMapper) {
        this.jpaCategoriaRepository = jpaCategoriaRepository;
        this.categoriaMapper = categoriaMapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public Categoria registrar(Categoria categoria) {
        JpaCategoriaEntity entidade = categoriaMapper.domainToJpaCategoria(categoria);
        JpaCategoriaEntity novaCategoria = jpaCategoriaRepository.save(entidade);
        return categoriaMapper.jpaCategoriaToDomain(novaCategoria);
    }

    @Override
    public void deletar(Usuario usuario, Long idcategoria) {
        JpaUsuarioEntity usuarioEntity = usuarioMapper.usuarioToJpaUsuario(usuario);
        jpaCategoriaRepository.deletarPorUsuario(usuarioEntity, idcategoria);
    }

    @Override
    public List<Categoria> buscarTodas(Usuario usuario) {
        JpaUsuarioEntity usuarioEntity = usuarioMapper.usuarioToJpaUsuario(usuario);
        List<JpaCategoriaEntity> jpaCategoriaEntityList = jpaCategoriaRepository.buscarCategoriaPorIdEUsuario(usuarioEntity);
        return jpaCategoriaEntityList.stream().map(categoriaMapper::jpaCategoriaToDomain).toList();
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoria) {
        JpaCategoriaEntity entidadeJpa = categoriaMapper.domainToJpaCategoria(categoria);
        JpaCategoriaEntity atualizada = jpaCategoriaRepository.save(entidadeJpa);
        return categoriaMapper.jpaCategoriaToDomain(atualizada);

    }

    @Override
    public Optional<Categoria> buscarCategoriaId(Usuario usuario, Long id) {
        JpaUsuarioEntity usuarioEntity = usuarioMapper.usuarioToJpaUsuario(usuario);
        Optional<JpaCategoriaEntity> resultado = jpaCategoriaRepository.buscarPorIdEUsuario(usuarioEntity, id);
        return resultado.map(categoriaMapper::jpaCategoriaToDomain);
    }
}
