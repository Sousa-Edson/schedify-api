package com.schedify.schedify_api.interfaces.dto;

import java.time.LocalDateTime;

public record SlotResponse(
    LocalDateTime inicio,
    LocalDateTime fim
) {}
