package com.schedify.schedify_api.infrastructure.persistence.adapter;

import com.schedify.schedify_api.domain.model.Bloqueio;
import com.schedify.schedify_api.domain.port.BloqueioRepositoryPort;
import com.schedify.schedify_api.infrastructure.persistence.mapper.BloqueioEntityMapper;
import com.schedify.schedify_api.infrastructure.persistence.repository.BloqueioJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BloqueioRepositoryImpl implements BloqueioRepositoryPort {

    private final BloqueioJpaRepository jpaRepository;
    private final BloqueioEntityMapper mapper;

    public BloqueioRepositoryImpl(BloqueioJpaRepository jpaRepository, BloqueioEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Bloqueio salvar(Bloqueio bloqueio) {
        var entity = mapper.toEntity(bloqueio);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Bloqueio> buscarPorProfissionalEPeriodo(Long profissionalId, LocalDateTime inicio, LocalDateTime fim) {
        return jpaRepository.findByProfissionalIdAndPeriodo(profissionalId, inicio, fim)
                .stream().map(mapper::toDomain).toList();
    }

}
