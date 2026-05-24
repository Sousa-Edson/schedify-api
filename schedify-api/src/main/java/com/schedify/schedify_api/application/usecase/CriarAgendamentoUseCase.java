package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.entity.Agendamento;
import com.schedify.schedify_api.domain.entity.Servico;
import com.schedify.schedify_api.domain.entity.Usuario;
import com.schedify.schedify_api.domain.repository.AgendamentoRepository;
import com.schedify.schedify_api.domain.repository.ServicoRepository;
import com.schedify.schedify_api.domain.repository.UsuarioRepository;
import com.schedify.schedify_api.domain.service.ValidacaoConflitoService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarAgendamentoUseCase {

    private final UsuarioRepository usuarioRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final ValidacaoConflitoService validacaoConflitoService;

    public CriarAgendamentoUseCase(UsuarioRepository usuarioRepository,
                                    ServicoRepository servicoRepository,
                                    AgendamentoRepository agendamentoRepository,
                                    ValidacaoConflitoService validacaoConflitoService) {
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.validacaoConflitoService = validacaoConflitoService;
    }

    @Transactional
    public Agendamento executar(Long usuarioId, Long servicoId, LocalDateTime dataHoraInicio) {
        var usuario = usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + usuarioId));
        var servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado: " + servicoId));

        var dataHoraFim = dataHoraInicio.plusMinutes(servico.getDuracaoMinutos());
        var novoAgendamento = new Agendamento(usuarioId, servicoId, dataHoraInicio, dataHoraFim);

        var existentes = buscarAgendamentosConflitantes(servicoId, dataHoraInicio, dataHoraFim);

        if (validacaoConflitoService.temConflito(novoAgendamento, existentes)) {
            throw new IllegalStateException(
                "Conflito de horário: já existe um agendamento neste período"
            );
        }

        return agendamentoRepository.salvar(novoAgendamento);
    }

    private List<Agendamento> buscarAgendamentosConflitantes(Long servicoId,
                                                              LocalDateTime inicio,
                                                              LocalDateTime fim) {
        var inicioDia = inicio.toLocalDate().atStartOfDay();
        var fimDia = inicio.toLocalDate().atTime(java.time.LocalTime.MAX);
        return agendamentoRepository.buscarPorServicoEPeriodo(servicoId, inicioDia, fimDia);
    }
}
