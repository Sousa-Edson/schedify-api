package com.schedify.schedify_api.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CriarServicoRequest(
    @NotBlank(message = "Nome do serviço é obrigatório")
    String nome,

    @Positive(message = "Duração deve ser maior que zero")
    int duracaoMinutos
) {}
