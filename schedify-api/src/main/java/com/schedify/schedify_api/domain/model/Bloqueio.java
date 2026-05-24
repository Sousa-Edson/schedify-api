package com.schedify.schedify_api.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Bloqueio {

    private Long id;
    private Long profissionalId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String motivo;

    public Bloqueio() {}

    public Bloqueio(Long profissionalId, LocalDateTime dataInicio, LocalDateTime dataFim, String motivo) {
        this.profissionalId = profissionalId;
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setMotivo(motivo);
    }

    public Bloqueio(Long id, Long profissionalId, LocalDateTime dataInicio, LocalDateTime dataFim, String motivo) {
        this.id = id;
        this.profissionalId = profissionalId;
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setMotivo(motivo);
    }

    public Long getId() { return id; }
    public Long getProfissionalId() { return profissionalId; }
    public LocalDateTime getDataInicio() { return dataInicio; }
    public LocalDateTime getDataFim() { return dataFim; }
    public String getMotivo() { return motivo; }

    public void setDataInicio(LocalDateTime dataInicio) {
        if (dataInicio == null) throw new IllegalArgumentException("Data início do bloqueio é obrigatória");
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDateTime dataFim) {
        if (dataFim == null) throw new IllegalArgumentException("Data fim do bloqueio é obrigatória");
        if (dataInicio != null && !dataFim.isAfter(dataInicio))
            throw new IllegalArgumentException("Data fim deve ser posterior à data início");
        this.dataFim = dataFim;
    }

    public void setMotivo(String motivo) {
        if (motivo == null || motivo.trim().isBlank())
            throw new IllegalArgumentException("Motivo do bloqueio é obrigatório");
        this.motivo = motivo.trim();
    }

    public boolean intercepta(LocalDateTime inicio, LocalDateTime fim) {
        return inicio.isBefore(dataFim) && fim.isAfter(dataInicio);
    }

}
