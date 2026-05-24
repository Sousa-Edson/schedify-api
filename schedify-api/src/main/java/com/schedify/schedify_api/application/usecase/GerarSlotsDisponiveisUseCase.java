package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.application.dto.SlotDTO;
import com.schedify.schedify_api.domain.entity.Agendamento;
import com.schedify.schedify_api.domain.entity.Disponibilidade;
import com.schedify.schedify_api.domain.entity.Servico;
import com.schedify.schedify_api.domain.repository.AgendamentoRepository;
import com.schedify.schedify_api.domain.repository.DisponibilidadeRepository;
import com.schedify.schedify_api.domain.repository.ServicoRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GerarSlotsDisponiveisUseCase {

    private static final int INTERVALO_PADRAO_MINUTOS = 15;

    private final ServicoRepository servicoRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final AgendamentoRepository agendamentoRepository;

    @Value("${schedify.slot.intervalo-minutos:15}")
    private int intervaloMinutos;

    public GerarSlotsDisponiveisUseCase(ServicoRepository servicoRepository,
                                         DisponibilidadeRepository disponibilidadeRepository,
                                         AgendamentoRepository agendamentoRepository) {
        this.servicoRepository = servicoRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<SlotDTO> executar(LocalDate data, Long servicoId) {
        var servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado: " + servicoId));

        var diaSemana = data.getDayOfWeek();
        var disponibilidades = disponibilidadeRepository.buscarPorDiaSemana(diaSemana);

        if (disponibilidades.isEmpty()) {
            return List.of();
        }

        var inicioDia = data.atStartOfDay();
        var fimDia = data.atTime(LocalTime.MAX);

        var existentes = agendamentoRepository
                .buscarPorServicoEPeriodo(servicoId, inicioDia, fimDia);

        var slots = new ArrayList<SlotDTO>();

        for (var disp : disponibilidades) {
            slots.addAll(gerarSlotsParaDisponibilidade(data, servico, disp, existentes));
        }

        return slots;
    }

    private List<SlotDTO> gerarSlotsParaDisponibilidade(LocalDate data, Servico servico,
                                                         Disponibilidade disp,
                                                         List<Agendamento> existentes) {
        var slots = new ArrayList<SlotDTO>();
        var horaAtual = disp.getHoraInicio();
        var passo = intervaloMinutos > 0 ? intervaloMinutos : INTERVALO_PADRAO_MINUTOS;

        while (!horaAtual.plusMinutes(servico.getDuracaoMinutos()).isAfter(disp.getHoraFim())) {
            var inicioSlot = LocalDateTime.of(data, horaAtual);
            var fimSlot = inicioSlot.plusMinutes(servico.getDuracaoMinutos());

            if (naoTemConflito(inicioSlot, fimSlot, existentes)) {
                slots.add(new SlotDTO(inicioSlot, fimSlot));
            }

            horaAtual = horaAtual.plusMinutes(passo);
        }

        return slots;
    }

    private boolean naoTemConflito(LocalDateTime inicio, LocalDateTime fim,
                                    List<Agendamento> existentes) {
        return existentes.stream().noneMatch(e ->
            inicio.isBefore(e.getDataHoraFim()) && fim.isAfter(e.getDataHoraInicio())
        );
    }
}
