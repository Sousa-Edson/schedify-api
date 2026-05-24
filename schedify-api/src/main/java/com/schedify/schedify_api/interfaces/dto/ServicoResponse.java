package com.schedify.schedify_api.interfaces.dto;

import java.util.Set;

public record ServicoResponse(
    Long id,
    String nome,
    int duracaoMinutos,
    Set<Long> profissionalIds
) {}
