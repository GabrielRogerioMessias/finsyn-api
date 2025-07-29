package com.messias.finsyn.adapters.outbounds.repositories.meta_financeira;

import com.messias.finsyn.adapters.outbounds.entities.JpaMetaFinanceiraEntity;
import com.messias.finsyn.adapters.outbounds.entities.JpaUsuarioEntity;
import com.messias.finsyn.adapters.outbounds.entities.mappers.MetaFinanceiraMapper;
import com.messias.finsyn.adapters.outbounds.entities.mappers.UsuarioMapper;
import com.messias.finsyn.domain.models.meta_financeira.MetaFinanceira;
import com.messias.finsyn.domain.models.usuario.Usuario;
import com.messias.finsyn.domain.ports.out.MetaFinanceiraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MetaFinanceiraRepositoryImpl implements MetaFinanceiraRepository {
    private final JpaMetaFinanceiraRepository repository;
    private final MetaFinanceiraMapper mapper;
    private final UsuarioMapper usuarioMapper;

    public MetaFinanceiraRepositoryImpl(JpaMetaFinanceiraRepository repository,
                                        MetaFinanceiraMapper mapper,
                                        UsuarioMapper usuarioMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public MetaFinanceira criarMeta(MetaFinanceira metaFinanceira) {
        JpaMetaFinanceiraEntity jpaEntity = mapper.domainToJpa(metaFinanceira);
        return mapper.jpaToDomain(repository.save(jpaEntity));
    }

    @Override
    public void deletarMeta(MetaFinanceira metaFinanceira) {
        JpaMetaFinanceiraEntity jpaEntity = mapper.domainToJpa(metaFinanceira);
        repository.delete(jpaEntity);
    }

    @Override
    public List<MetaFinanceira> buscarTodas(Usuario usuario) {
        JpaUsuarioEntity jpaUsuario = usuarioMapper.usuarioToJpaUsuario(usuario);
        return repository.buscarTodas(jpaUsuario)
                .stream()
                .map(mapper::jpaToDomain)
                .toList();
    }

    @Override
    public MetaFinanceira atualizarMeta(MetaFinanceira metaFinanceira) {
        JpaMetaFinanceiraEntity jpaEntity = mapper.domainToJpa(metaFinanceira);
        return mapper.jpaToDomain(repository.save(jpaEntity));
    }

    @Override
    public Optional<MetaFinanceira> buscarPorId(Usuario usuario, Long idMeta) {
        JpaUsuarioEntity jpaUsuario = usuarioMapper.usuarioToJpaUsuario(usuario);
        Optional<JpaMetaFinanceiraEntity> entity = repository.buscarPorId(jpaUsuario, idMeta);
        return entity.map(mapper::jpaToDomain);
    }
}
