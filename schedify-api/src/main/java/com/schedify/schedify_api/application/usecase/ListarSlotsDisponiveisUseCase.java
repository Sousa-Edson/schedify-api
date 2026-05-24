package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.port.AgendamentoRepositoryPort;
import com.schedify.schedify_api.domain.port.BloqueioRepositoryPort;
import com.schedify.schedify_api.domain.port.DisponibilidadeRepositoryPort;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import com.schedify.schedify_api.domain.service.AgendaService;
import com.schedify.schedify_api.domain.service.AgendaService.Slot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ListarSlotsDisponiveisUseCase {

    private final ServicoRepositoryPort servicoRepository;
    private final ProfissionalRepositoryPort profissionalRepository;
    private final DisponibilidadeRepositoryPort disponibilidadeRepository;
    private final AgendamentoRepositoryPort agendamentoRepository;
    private final BloqueioRepositoryPort bloqueioRepository;
    private final AgendaService agendaService;

    @Value("${schedify.slot.intervalo-minutos:15}")
    private int intervaloMinutos;

    public ListarSlotsDisponiveisUseCase(ServicoRepositoryPort servicoRepository,
                                          ProfissionalRepositoryPort profissionalRepository,
                                          DisponibilidadeRepositoryPort disponibilidadeRepository,
                                          AgendamentoRepositoryPort agendamentoRepository,
                                          BloqueioRepositoryPort bloqueioRepository,
                                          AgendaService agendaService) {
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.bloqueioRepository = bloqueioRepository;
        this.agendaService = agendaService;
    }

    public List<Slot> executar(LocalDate data, Long servicoId, Long profissionalId) {
        var servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
        var profissional = profissionalRepository.buscarPorId(profissionalId)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        if (!profissional.prestaServico(servico))
            throw new IllegalArgumentException("Profissional não presta este serviço");

        var diaSemana = data.getDayOfWeek();
        var disponibilidades = disponibilidadeRepository.buscarPorProfissionalEDiaSemana(profissionalId, diaSemana);

        if (disponibilidades.isEmpty())
            return List.of();

        var inicioDia = data.atStartOfDay();
        var fimDia = data.atTime(LocalTime.MAX);
        var ocupados = agendamentoRepository.buscarPorProfissionalEPeriodo(profissionalId, inicioDia, fimDia);
        var bloqueios = bloqueioRepository.buscarPorProfissionalEPeriodo(profissionalId, inicioDia, fimDia);

        return agendaService.gerarSlots(data, servico, disponibilidades, ocupados, bloqueios, intervaloMinutos);
    }

}
