package com.schedify.schedify_api.domain.service;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.model.Disponibilidade;
import com.schedify.schedify_api.domain.model.Servico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GeracaoSlotsService {

    public List<Slot> gerarSlots(LocalDate data, Servico servico,
                                  List<Disponibilidade> disponibilidades,
                                  List<Agendamento> ocupados,
                                  int intervaloMinutos) {
        var slots = new ArrayList<Slot>();
        for (var disp : disponibilidades)
            slots.addAll(gerarSlotsParaDisponibilidade(data, servico, disp, ocupados, intervaloMinutos));
        return slots;
    }

    private List<Slot> gerarSlotsParaDisponibilidade(LocalDate data, Servico servico,
                                                      Disponibilidade disp,
                                                      List<Agendamento> ocupados,
                                                      int intervaloMinutos) {
        var slots = new ArrayList<Slot>();
        var horaAtual = disp.getHoraInicio();
        var passo = intervaloMinutos > 0 ? intervaloMinutos : 15;

        while (!horaAtual.plusMinutes(servico.getDuracaoMinutos()).isAfter(disp.getHoraFim())) {
            var inicioSlot = LocalDateTime.of(data, horaAtual);
            var fimSlot = inicioSlot.plusMinutes(servico.getDuracaoMinutos());
            if (naoTemConflito(inicioSlot, fimSlot, ocupados))
                slots.add(new Slot(inicioSlot, fimSlot));
            horaAtual = horaAtual.plusMinutes(passo);
        }
        return slots;
    }

    private boolean naoTemConflito(LocalDateTime inicio, LocalDateTime fim, List<Agendamento> ocupados) {
        return ocupados.stream().noneMatch(e ->
            inicio.isBefore(e.getDataHoraFim()) && fim.isAfter(e.getDataHoraInicio()));
    }

    public record Slot(LocalDateTime inicio, LocalDateTime fim) {}

}
