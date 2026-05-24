package com.schedify.schedify_api.interfaces.dto;

import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioRequest(
    @NotBlank(message = "Nome do usuário é obrigatório")
    String nome
) {}
