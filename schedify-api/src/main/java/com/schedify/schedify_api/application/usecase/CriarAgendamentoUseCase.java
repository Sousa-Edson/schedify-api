package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.port.AgendamentoRepositoryPort;
import com.schedify.schedify_api.domain.port.BloqueioRepositoryPort;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import com.schedify.schedify_api.domain.port.UsuarioRepositoryPort;
import com.schedify.schedify_api.domain.service.AgendaService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarAgendamentoUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final ServicoRepositoryPort servicoRepository;
    private final ProfissionalRepositoryPort profissionalRepository;
    private final AgendamentoRepositoryPort agendamentoRepository;
    private final BloqueioRepositoryPort bloqueioRepository;
    private final AgendaService agendaService;

    public CriarAgendamentoUseCase(UsuarioRepositoryPort usuarioRepository,
                                    ServicoRepositoryPort servicoRepository,
                                    ProfissionalRepositoryPort profissionalRepository,
                                    AgendamentoRepositoryPort agendamentoRepository,
                                    BloqueioRepositoryPort bloqueioRepository,
                                    AgendaService agendaService) {
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.bloqueioRepository = bloqueioRepository;
        this.agendaService = agendaService;
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

        var existentes = buscarAgendamentosConflitantesComLock(profissionalId, agendamento.getDataHoraInicio(), agendamento.getDataHoraFim());

        if (agendaService.temConflito(agendamento, existentes))
            throw new IllegalStateException("Conflito de horário: já existe um agendamento neste período");

        return agendamentoRepository.salvar(agendamento);
    }

    private List<Agendamento> buscarAgendamentosConflitantesComLock(Long profissionalId, LocalDateTime inicio, LocalDateTime fim) {
        return agendamentoRepository.buscarPorProfissionalEPeriodoComLock(profissionalId, inicio, fim);
    }

}
