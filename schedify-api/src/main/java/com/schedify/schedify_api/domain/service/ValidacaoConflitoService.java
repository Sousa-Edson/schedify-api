package com.schedify.schedify_api.domain.service;

import com.schedify.schedify_api.domain.entity.Agendamento;
import java.util.List;

public class ValidacaoConflitoService {

    public boolean temConflito(Agendamento novo, List<Agendamento> existentes) {
        if (novo.getDataHoraInicio() == null || novo.getDataHoraFim() == null) {
            throw new IllegalArgumentException("Data/hora de início e fim são obrigatórias");
        }
        if (!novo.getDataHoraFim().isAfter(novo.getDataHoraInicio())) {
            throw new IllegalArgumentException("Data/hora fim deve ser posterior à data/hora início");
        }
        return existentes.stream().anyMatch(existente -> isSobreposto(novo, existente));
    }

    private boolean isSobreposto(Agendamento a, Agendamento b) {
        return a.getDataHoraInicio().isBefore(b.getDataHoraFim())
            && a.getDataHoraFim().isAfter(b.getDataHoraInicio());
    }
}
