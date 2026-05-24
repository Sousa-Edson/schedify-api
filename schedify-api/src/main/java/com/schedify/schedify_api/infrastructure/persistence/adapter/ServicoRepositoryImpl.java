package com.schedify.schedify_api.infrastructure.persistence.adapter;

import com.schedify.schedify_api.domain.model.Servico;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import com.schedify.schedify_api.infrastructure.persistence.mapper.ServicoEntityMapper;
import com.schedify.schedify_api.infrastructure.persistence.repository.ServicoJpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ServicoRepositoryImpl implements ServicoRepositoryPort {

    private final ServicoJpaRepository jpaRepository;
    private final ServicoEntityMapper mapper;

    public ServicoRepositoryImpl(ServicoJpaRepository jpaRepository, ServicoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Servico salvar(Servico servico) {
        var entity = mapper.toEntity(servico);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Servico> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

}
