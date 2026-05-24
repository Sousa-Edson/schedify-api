package com.schedify.schedify_api.interfaces.dto;

import jakarta.validation.constraints.NotBlank;

public record CancelarAgendamentoRequest(
    @NotBlank(message = "Motivo do cancelamento é obrigatório")
    String motivo
) {}
