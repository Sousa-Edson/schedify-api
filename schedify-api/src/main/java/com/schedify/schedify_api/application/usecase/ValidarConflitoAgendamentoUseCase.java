package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.entity.Agendamento;
import com.schedify.schedify_api.domain.repository.AgendamentoRepository;
import com.schedify.schedify_api.domain.service.ValidacaoConflitoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ValidarConflitoAgendamentoUseCase {

    private final AgendamentoRepository agendamentoRepository;
    private final ValidacaoConflitoService validacaoConflitoService;

    public ValidarConflitoAgendamentoUseCase(AgendamentoRepository agendamentoRepository,
                                              ValidacaoConflitoService validacaoConflitoService) {
        this.agendamentoRepository = agendamentoRepository;
        this.validacaoConflitoService = validacaoConflitoService;
    }

    public boolean executar(Long servicoId, LocalDateTime inicio, LocalDateTime fim) {
        if (!fim.isAfter(inicio)) {
            throw new IllegalArgumentException("Data/hora fim deve ser posterior à data/hora início");
        }

        var inicioDia = inicio.toLocalDate().atStartOfDay();
        var fimDia = inicio.toLocalDate().atTime(LocalTime.MAX);

        var existentes = agendamentoRepository.buscarPorServicoEPeriodo(servicoId, inicioDia, fimDia);

        var proposto = new Agendamento(null, servicoId, inicio, fim);
        return validacaoConflitoService.temConflito(proposto, existentes);
    }
}
