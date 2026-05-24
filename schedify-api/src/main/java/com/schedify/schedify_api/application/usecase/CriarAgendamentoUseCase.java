package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.model.Servico;
import com.schedify.schedify_api.domain.port.AgendamentoRepositoryPort;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import com.schedify.schedify_api.domain.port.UsuarioRepositoryPort;
import com.schedify.schedify_api.domain.service.ValidacaoConflitoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarAgendamentoUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final ServicoRepositoryPort servicoRepository;
    private final ProfissionalRepositoryPort profissionalRepository;
    private final AgendamentoRepositoryPort agendamentoRepository;
    private final ValidacaoConflitoService validacaoConflitoService;

    public CriarAgendamentoUseCase(UsuarioRepositoryPort usuarioRepository,
                                    ServicoRepositoryPort servicoRepository,
                                    ProfissionalRepositoryPort profissionalRepository,
                                    AgendamentoRepositoryPort agendamentoRepository,
                                    ValidacaoConflitoService validacaoConflitoService) {
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.validacaoConflitoService = validacaoConflitoService;
    }

    @Transactional
    public Agendamento executar(Long usuarioId, Long servicoId, Long profissionalId, LocalDateTime dataHoraInicio) {
        var usuario = usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        var servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
        var profissional = profissionalRepository.buscarPorId(profissionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        if (!profissional.prestaServico(servico))
            throw new IllegalArgumentException("Profissional não presta este serviço");

        var agendamento = Agendamento.calcularFim(usuarioId, servicoId, profissionalId, dataHoraInicio, servico);

        var existentes = buscarAgendamentosConflitantes(profissionalId, dataHoraInicio, agendamento.getDataHoraFim());

        if (validacaoConflitoService.temConflito(agendamento, existentes))
            throw new IllegalStateException("Conflito de horário: já existe um agendamento neste período");

        return agendamentoRepository.salvar(agendamento);
    }

    private java.util.List<Agendamento> buscarAgendamentosConflitantes(Long profissionalId, LocalDateTime inicio, LocalDateTime fim) {
        var inicioDia = inicio.toLocalDate().atStartOfDay();
        var fimDia = inicio.toLocalDate().atTime(LocalTime.MAX);
        return agendamentoRepository.buscarPorProfissionalEPeriodo(profissionalId, inicioDia, fimDia);
    }

}
