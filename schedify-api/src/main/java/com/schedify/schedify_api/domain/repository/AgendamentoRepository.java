package com.schedify.schedify_api.domain.repository;

import com.schedify.schedify_api.domain.entity.Agendamento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository {
    Agendamento salvar(Agendamento agendamento);
    Optional<Agendamento> buscarPorId(Long id);
    List<Agendamento> buscarPorServicoEPeriodo(Long servicoId, LocalDateTime inicio, LocalDateTime fim);
    List<Agendamento> buscarPorUsuarioId(Long usuarioId);
}
