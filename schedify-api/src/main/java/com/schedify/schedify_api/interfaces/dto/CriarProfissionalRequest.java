package com.schedify.schedify_api.interfaces.dto;

import jakarta.validation.constraints.NotBlank;

public record CriarProfissionalRequest(
    @NotBlank(message = "Nome do profissional é obrigatório")
    String nome
) {}
