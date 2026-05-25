package com.schedify.schedify_api.infrastructure.persistence.adapter;

import com.schedify.schedify_api.domain.model.Profissional;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.infrastructure.persistence.mapper.ProfissionalEntityMapper;
import com.schedify.schedify_api.infrastructure.persistence.repository.ProfissionalJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class ProfissionalRepositoryImpl implements ProfissionalRepositoryPort {

    private final ProfissionalJpaRepository jpaRepository;
    private final ProfissionalEntityMapper mapper;

    public ProfissionalRepositoryImpl(ProfissionalJpaRepository jpaRepository, ProfissionalEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Profissional salvar(Profissional profissional) {
        var entity = mapper.toEntity(profissional);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Profissional> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

}
