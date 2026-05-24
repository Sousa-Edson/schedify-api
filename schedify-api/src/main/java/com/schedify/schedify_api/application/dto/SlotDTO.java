package com.schedify.schedify_api.application.dto;

import java.time.LocalDateTime;

public class SlotDTO {

    private LocalDateTime inicio;
    private LocalDateTime fim;

    public SlotDTO() {
    }

    public SlotDTO(LocalDateTime inicio, LocalDateTime fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
}
