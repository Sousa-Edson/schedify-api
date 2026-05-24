package com.schedify.schedify_api.domain.model;

import java.time.LocalDateTime;

public class Agendamento {

    private Long id;
    private Long usuarioId;
    private Long servicoId;
    private Long profissionalId;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    public Agendamento() {}

    public Agendamento(Long usuarioId, Long servicoId, Long profissionalId,
                       LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.usuarioId = usuarioId;
        this.servicoId = servicoId;
        this.profissionalId = profissionalId;
        setDataHoraInicio(dataHoraInicio);
        setDataHoraFim(dataHoraFim);
    }

    public Agendamento(Long id, Long usuarioId, Long servicoId, Long profissionalId,
                       LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.servicoId = servicoId;
        this.profissionalId = profissionalId;
        setDataHoraInicio(dataHoraInicio);
        setDataHoraFim(dataHoraFim);
    }

    public Long getId() { return id; }
    public Long getUsuarioId() { return usuarioId; }
    public Long getServicoId() { return servicoId; }
    public Long getProfissionalId() { return profissionalId; }
    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public LocalDateTime getDataHoraFim() { return dataHoraFim; }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        if (dataHoraInicio == null)
            throw new IllegalArgumentException("Data/hora de início é obrigatória");
        if (dataHoraInicio.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Não é permitido agendar no passado");
        this.dataHoraInicio = dataHoraInicio;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        if (dataHoraFim == null)
            throw new IllegalArgumentException("Data/hora de fim é obrigatória");
        if (dataHoraInicio != null && !dataHoraFim.isAfter(dataHoraInicio))
            throw new IllegalArgumentException("Data/hora de fim deve ser posterior à data/hora de início");
        this.dataHoraFim = dataHoraFim;
    }

    public static Agendamento calcularFim(Long usuarioId, Long servicoId, Long profissionalId,
                                           LocalDateTime dataHoraInicio, Servico servico) {
        var fim = dataHoraInicio.plusMinutes(servico.getDuracaoMinutos());
        return new Agendamento(usuarioId, servicoId, profissionalId, dataHoraInicio, fim);
    }

}
