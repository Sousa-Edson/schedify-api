package com.schedify.schedify_api.infrastructure.adapter;

import com.schedify.schedify_api.domain.entity.Agendamento;
import com.schedify.schedify_api.domain.repository.AgendamentoRepository;
import com.schedify.schedify_api.infrastructure.persistence.mapper.AgendamentoEntityMapper;
import com.schedify.schedify_api.infrastructure.repository.AgendamentoJpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AgendamentoRepositoryImpl implements AgendamentoRepository {

    private final AgendamentoJpaRepository jpaRepository;
    private final AgendamentoEntityMapper mapper;

    public AgendamentoRepositoryImpl(AgendamentoJpaRepository jpaRepository,
                                      AgendamentoEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Agendamento salvar(Agendamento agendamento) {
        var entity = mapper.toEntity(agendamento);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Agendamento> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Agendamento> buscarPorServicoEPeriodo(Long servicoId,
                                                       LocalDateTime inicio,
                                                       LocalDateTime fim) {
        return jpaRepository
                .findByServicoIdAndDataHoraInicioBetween(servicoId, inicio, fim)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Agendamento> buscarPorUsuarioId(Long usuarioId) {
        return jpaRepository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
