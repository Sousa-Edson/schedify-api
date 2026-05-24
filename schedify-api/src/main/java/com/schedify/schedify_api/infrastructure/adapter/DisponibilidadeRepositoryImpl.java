package com.schedify.schedify_api.infrastructure.adapter;

import com.schedify.schedify_api.domain.entity.Disponibilidade;
import com.schedify.schedify_api.domain.repository.DisponibilidadeRepository;
import com.schedify.schedify_api.infrastructure.persistence.mapper.DisponibilidadeEntityMapper;
import com.schedify.schedify_api.infrastructure.repository.DisponibilidadeJpaRepository;
import java.time.DayOfWeek;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DisponibilidadeRepositoryImpl implements DisponibilidadeRepository {

    private final DisponibilidadeJpaRepository jpaRepository;
    private final DisponibilidadeEntityMapper mapper;

    public DisponibilidadeRepositoryImpl(DisponibilidadeJpaRepository jpaRepository,
                                          DisponibilidadeEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Disponibilidade salvar(Disponibilidade disponibilidade) {
        var entity = mapper.toEntity(disponibilidade);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Disponibilidade> buscarPorDiaSemana(DayOfWeek diaSemana) {
        return jpaRepository
                .findByDiaSemana(diaSemana.getValue())
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
