package com.schedify.schedify_api.domain.service;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.model.Bloqueio;
import com.schedify.schedify_api.domain.model.Disponibilidade;
import com.schedify.schedify_api.domain.model.Servico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AgendaService {

    public record Slot(LocalDateTime inicio, LocalDateTime fim) {}

    public List<Slot> gerarSlots(LocalDate data, Servico servico,
                                  List<Disponibilidade> disponibilidades,
                                  List<Agendamento> ocupados,
                                  List<Bloqueio> bloqueios,
                                  int intervaloMinutos) {
        var slots = new ArrayList<Slot>();
        for (var disp : disponibilidades)
            slots.addAll(gerarSlotsParaDisponibilidade(data, servico, disp, ocupados, bloqueios, intervaloMinutos));
        return slots;
    }

    public Slot sugerirMelhorHorario(List<Slot> slots, List<Agendamento> existentes) {
        if (slots.isEmpty()) return null;
        var agora = LocalDateTime.now();
        return slots.stream()
                .filter(s -> s.inicio().isAfter(agora))
                .min(Comparator.comparing(s -> calcularOciosidade(s, existentes)))
                .orElse(slots.getFirst());
    }

    private long calcularOciosidade(Slot slot, List<Agendamento> existentes) {
        return existentes.stream()
                .filter(a -> a.getStatus() != com.schedify.schedify_api.domain.model.StatusAgendamento.CANCELADO)
                .mapToLong(a -> Math.abs(
                        a.getDataHoraFim().toLocalTime().toSecondOfDay()
                        - slot.inicio().toLocalTime().toSecondOfDay()))
                .sum();
    }

    private List<Slot> gerarSlotsParaDisponibilidade(LocalDate data, Servico servico,
                                                      Disponibilidade disp,
                                                      List<Agendamento> ocupados,
                                                      List<Bloqueio> bloqueios,
                                                      int intervaloMinutos) {
        var slots = new ArrayList<Slot>();
        var horaAtual = disp.getHoraInicio();
        var passo = intervaloMinutos > 0 ? intervaloMinutos : 15;
        var totalMinutos = servico.getTotalMinutos();

        while (!horaAtual.plusMinutes(totalMinutos).isAfter(disp.getHoraFim())) {
            var inicioSlot = LocalDateTime.of(data, horaAtual);
            var fimSlot = inicioSlot.plusMinutes(totalMinutos);

            if (estaDisponivel(inicioSlot, fimSlot, ocupados, bloqueios))
                slots.add(new Slot(inicioSlot, fimSlot));

            horaAtual = horaAtual.plusMinutes(passo);
        }
        return slots;
    }

    private boolean estaDisponivel(LocalDateTime inicio, LocalDateTime fim,
                                    List<Agendamento> ocupados,
                                    List<Bloqueio> bloqueios) {
        var semConflito = ocupados.stream().noneMatch(e ->
                inicio.isBefore(e.getDataHoraFim()) && fim.isAfter(e.getDataHoraInicio()));
        var semBloqueio = bloqueios.stream().noneMatch(b -> b.intercepta(inicio, fim));
        return semConflito && semBloqueio;
    }

    public boolean temConflito(Agendamento novo, List<Agendamento> existentes) {
        if (novo.getDataHoraInicio() == null || novo.getDataHoraFim() == null)
            throw new IllegalArgumentException("Data/hora de início e fim são obrigatórias");
        if (!novo.getDataHoraFim().isAfter(novo.getDataHoraInicio()))
            throw new IllegalArgumentException("Data/hora fim deve ser posterior à data/hora início");
        return existentes.stream().anyMatch(e -> isSobreposto(e, novo));
    }

    private boolean isSobreposto(Agendamento existente, Agendamento novo) {
        return novo.getDataHoraInicio().isBefore(existente.getDataHoraFim())
            && novo.getDataHoraFim().isAfter(existente.getDataHoraInicio());
    }

}
