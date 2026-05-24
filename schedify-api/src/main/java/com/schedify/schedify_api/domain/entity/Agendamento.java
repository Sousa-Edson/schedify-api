package com.schedify.schedify_api.domain.entity;

import java.time.LocalDateTime;

public class Agendamento {

    private Long id;
    private Long usuarioId;
    private Long servicoId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    public Agendamento() {
    }

    public Agendamento(Long usuarioId, Long servicoId,
                       LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.usuarioId = usuarioId;
        this.servicoId = servicoId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }

    public Agendamento(Long id, Long usuarioId, Long servicoId,
                       LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.servicoId = servicoId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }
}
