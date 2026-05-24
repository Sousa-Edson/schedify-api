package com.schedify.schedify_api.domain.port;

import com.schedify.schedify_api.domain.model.Bloqueio;
import java.time.LocalDateTime;
import java.util.List;

public interface BloqueioRepositoryPort {
    Bloqueio salvar(Bloqueio bloqueio);
    List<Bloqueio> buscarPorProfissionalEPeriodo(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
}
