package com.schedify.schedify_api.interfaces.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CriarAgendamentoRequest(
    @NotNull(message = "ID do usuário é obrigatório")
    Long usuarioId,

    @NotNull(message = "ID do serviço é obrigatório")
    Long servicoId,

    @NotNull(message = "ID do profissional é obrigatório")
    Long profissionalId,

    @NotNull(message = "Data/hora de início é obrigatória")
    LocalDateTime dataHoraInicio
) {}
