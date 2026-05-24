package com.schedify.schedify_api.domain.port;

import com.schedify.schedify_api.domain.model.Disponibilidade;
import java.time.DayOfWeek;
import java.util.List;

public interface DisponibilidadeRepositoryPort {
    Disponibilidade salvar(Disponibilidade disponibilidade);
    List<Disponibilidade> buscarPorDiaSemana(DayOfWeek diaSemana);
    List<Disponibilidade> buscarPorProfissionalEDiaSemana(Long profissionalId, DayOfWeek diaSemana);
}
