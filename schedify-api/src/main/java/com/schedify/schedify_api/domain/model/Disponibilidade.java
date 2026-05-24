package com.schedify.schedify_api.domain.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Disponibilidade {

    private Long id;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private Long profissionalId;

    public Disponibilidade() {}

    public Disponibilidade(DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim, Long profissionalId) {
        setDiaSemana(diaSemana);
        setHoraInicio(horaInicio);
        setHoraFim(horaFim);
        setProfissionalId(profissionalId);
    }

    public Disponibilidade(Long id, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim, Long profissionalId) {
        this.id = id;
        setDiaSemana(diaSemana);
        setHoraInicio(horaInicio);
        setHoraFim(horaFim);
        setProfissionalId(profissionalId);
    }

    public Long getId() { return id; }
    public DayOfWeek getDiaSemana() { return diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFim() { return horaFim; }
    public Long getProfissionalId() { return profissionalId; }

    public void setDiaSemana(DayOfWeek diaSemana) {
        if (diaSemana == null)
            throw new IllegalArgumentException("Dia da semana é obrigatório");
        this.diaSemana = diaSemana;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        if (horaInicio == null)
            throw new IllegalArgumentException("Hora de início é obrigatória");
        this.horaInicio = horaInicio;
    }

    public void setHoraFim(LocalTime horaFim) {
        if (horaFim == null)
            throw new IllegalArgumentException("Hora de fim é obrigatória");
        if (horaInicio != null && !horaFim.isAfter(horaInicio))
            throw new IllegalArgumentException("Hora de fim deve ser posterior à hora de início");
        this.horaFim = horaFim;
    }

    public void setProfissionalId(Long profissionalId) {
        if (profissionalId == null)
            throw new IllegalArgumentException("Profissional é obrigatório");
        this.profissionalId = profissionalId;
    }

    public boolean contemHorario(LocalTime hora) {
        return !hora.isBefore(horaInicio) && hora.isBefore(horaFim);
    }

}
