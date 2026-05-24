package com.schedify.schedify_api.domain.port;

import com.schedify.schedify_api.domain.model.Agendamento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepositoryPort {
    Agendamento salvar(Agendamento agendamento);
    Optional<Agendamento> buscarPorId(Long id);
    List<Agendamento> buscarPorServicoEPeriodo(Long servicoId, LocalDateTime inicio, LocalDateTime fim);
    List<Agendamento> buscarPorProfissionalEPeriodo(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
    List<Agendamento> buscarPorProfissionalEPeriodoComLock(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
    List<Agendamento> buscarPorUsuarioId(Long usuarioId);
}
