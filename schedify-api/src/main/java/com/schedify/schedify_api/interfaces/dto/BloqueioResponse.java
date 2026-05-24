package com.schedify.schedify_api.interfaces.dto;

import java.time.LocalDateTime;

public record BloqueioResponse(
    Long id,
    Long profissionalId,
    LocalDateTime dataInicio,
    LocalDateTime dataFim,
    String motivo
) {}
