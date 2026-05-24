package com.schedify.schedify_api.infrastructure.persistence.adapter;

import com.schedify.schedify_api.domain.model.Disponibilidade;
import com.schedify.schedify_api.domain.port.DisponibilidadeRepositoryPort;
import com.schedify.schedify_api.infrastructure.persistence.mapper.DisponibilidadeEntityMapper;
import com.schedify.schedify_api.infrastructure.persistence.repository.DisponibilidadeJpaRepository;
import java.time.DayOfWeek;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class DisponibilidadeRepositoryImpl implements DisponibilidadeRepositoryPort {

    private final DisponibilidadeJpaRepository jpaRepository;
    private final DisponibilidadeEntityMapper mapper;

    public DisponibilidadeRepositoryImpl(DisponibilidadeJpaRepository jpaRepository, DisponibilidadeEntityMapper mapper) {
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
        return jpaRepository.findByDiaSemana(diaSemana.getValue())
                .stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Disponibilidade> buscarPorProfissionalEDiaSemana(Long profissionalId, DayOfWeek diaSemana) {
        return jpaRepository.findByProfissionalIdAndDiaSemana(profissionalId, diaSemana.getValue())
                .stream().map(mapper::toDomain).toList();
    }

}
