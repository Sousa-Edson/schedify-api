package com.schedify.schedify_api.domain.service;

import com.schedify.schedify_api.domain.model.Agendamento;
import java.util.List;

public class ValidacaoConflitoService {

    public boolean temConflito(Agendamento novo, List<Agendamento> existentes) {
        if (novo.getDataHoraInicio() == null || novo.getDataHoraFim() == null)
            throw new IllegalArgumentException("Data/hora de início e fim são obrigatórias");
        if (!novo.getDataHoraFim().isAfter(novo.getDataHoraInicio()))
            throw new IllegalArgumentException("Data/hora fim deve ser posterior à data/hora início");
        return existentes.stream().anyMatch(e -> isSobreposto(e, novo));
    }

    public boolean temConflito(List<Agendamento> existentes, Agendamento novo) {
        return temConflito(novo, existentes);
    }

    private boolean isSobreposto(Agendamento existente, Agendamento novo) {
        return novo.getDataHoraInicio().isBefore(existente.getDataHoraFim())
            && novo.getDataHoraFim().isAfter(existente.getDataHoraInicio());
    }

}
