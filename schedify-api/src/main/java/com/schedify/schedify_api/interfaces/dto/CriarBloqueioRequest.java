package com.schedify.schedify_api.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CriarBloqueioRequest(
    @NotNull(message = "ID do profissional é obrigatório")
    Long profissionalId,

    @NotNull(message = "Data/hora de início é obrigatória")
    LocalDateTime dataInicio,

    @NotNull(message = "Data/hora de fim é obrigatória")
    LocalDateTime dataFim,

    @NotBlank(message = "Motivo é obrigatório")
    String motivo
) {}
