package com.schedify.schedify_api.interfaces.dto;

import java.time.LocalDateTime;

public record AgendamentoResponse(
    Long id,
    Long usuarioId,
    String nomeUsuario,
    Long servicoId,
    String nomeServico,
    Long profissionalId,
    String nomeProfissional,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim,
    String status,
    String motivoCancelamento
) {}
