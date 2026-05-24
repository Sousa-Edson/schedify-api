package com.schedify.schedify_api.infrastructure.adapter;

import com.schedify.schedify_api.domain.entity.Usuario;
import com.schedify.schedify_api.domain.repository.UsuarioRepository;
import com.schedify.schedify_api.infrastructure.persistence.mapper.UsuarioEntityMapper;
import com.schedify.schedify_api.infrastructure.repository.UsuarioJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioEntityMapper mapper;

    public UsuarioRepositoryImpl(UsuarioJpaRepository jpaRepository, UsuarioEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        var entity = mapper.toEntity(usuario);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
