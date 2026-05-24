package com.schedify.schedify_api.interfaces.dto;

import java.util.Set;

public record ProfissionalResponse(
    Long id,
    String nome,
    Set<Long> servicoIds
) {}
