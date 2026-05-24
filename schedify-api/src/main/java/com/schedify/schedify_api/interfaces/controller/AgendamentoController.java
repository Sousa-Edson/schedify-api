package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.usecase.CriarAgendamentoUseCase;
import com.schedify.schedify_api.domain.port.AgendamentoRepositoryPort;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import com.schedify.schedify_api.domain.port.UsuarioRepositoryPort;
import com.schedify.schedify_api.interfaces.dto.AgendamentoResponse;
import com.schedify.schedify_api.interfaces.dto.CriarAgendamentoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Operações relacionadas a agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final AgendamentoRepositoryPort agendamentoRepository;
    private final UsuarioRepositoryPort usuarioRepository;
    private final ServicoRepositoryPort servicoRepository;
    private final ProfissionalRepositoryPort profissionalRepository;

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase,
                                  AgendamentoRepositoryPort agendamentoRepository,
                                  UsuarioRepositoryPort usuarioRepository,
                                  ServicoRepositoryPort servicoRepository,
                                  ProfissionalRepositoryPort profissionalRepository) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @PostMapping
    @Operation(summary = "Criar um novo agendamento")
    public ResponseEntity<AgendamentoResponse> criar(@Valid @RequestBody CriarAgendamentoRequest request) {
        var agendamento = criarAgendamentoUseCase.executar(
                request.usuarioId(), request.servicoId(), request.profissionalId(), request.dataHoraInicio());
        return ResponseEntity.status(HttpStatus.CREATED).body(enriquecerResponse(agendamento));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar agendamentos de um usuário")
    public ResponseEntity<List<AgendamentoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        var agendamentos = agendamentoRepository.buscarPorUsuarioId(usuarioId);
        var response = agendamentos.stream().map(this::enriquecerResponse).toList();
        return ResponseEntity.ok(response);
    }

    private AgendamentoResponse enriquecerResponse(com.schedify.schedify_api.domain.model.Agendamento agendamento) {
        var nomeUsuario = usuarioRepository.buscarPorId(agendamento.getUsuarioId())
                .map(u -> u.getNome()).orElse(null);
        var nomeServico = servicoRepository.buscarPorId(agendamento.getServicoId())
                .map(s -> s.getNome()).orElse(null);
        var nomeProfissional = profissionalRepository.buscarPorId(agendamento.getProfissionalId())
                .map(p -> p.getNome()).orElse(null);
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getUsuarioId(), nomeUsuario,
                agendamento.getServicoId(), nomeServico,
                agendamento.getProfissionalId(), nomeProfissional,
                agendamento.getDataHoraInicio(), agendamento.getDataHoraFim());
    }

}
