package com.schedify.schedify_api.infrastructure.persistence.adapter;

import com.schedify.schedify_api.domain.model.Usuario;
import com.schedify.schedify_api.domain.port.UsuarioRepositoryPort;
import com.schedify.schedify_api.infrastructure.persistence.mapper.UsuarioEntityMapper;
import com.schedify.schedify_api.infrastructure.persistence.repository.UsuarioJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryPort {

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
