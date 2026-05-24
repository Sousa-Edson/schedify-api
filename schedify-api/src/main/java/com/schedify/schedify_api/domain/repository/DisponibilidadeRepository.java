package com.schedify.schedify_api.domain.repository;

import com.schedify.schedify_api.domain.entity.Disponibilidade;
import java.time.DayOfWeek;
import java.util.List;

public interface DisponibilidadeRepository {
    Disponibilidade salvar(Disponibilidade disponibilidade);
    List<Disponibilidade> buscarPorDiaSemana(DayOfWeek diaSemana);
}
