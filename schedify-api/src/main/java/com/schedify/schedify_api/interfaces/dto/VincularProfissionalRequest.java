package com.schedify.schedify_api.interfaces.dto;

import jakarta.validation.constraints.NotNull;

public record VincularProfissionalRequest(
    @NotNull(message = "ID do profissional é obrigatório")
    Long profissionalId
) {}
