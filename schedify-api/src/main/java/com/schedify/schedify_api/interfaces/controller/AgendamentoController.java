package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.usecase.CriarAgendamentoUseCase;
import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.port.AgendamentoRepositoryPort;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import com.schedify.schedify_api.domain.port.UsuarioRepositoryPort;
import com.schedify.schedify_api.interfaces.dto.AgendamentoResponse;
import com.schedify.schedify_api.interfaces.dto.CancelarAgendamentoRequest;
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

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase,
                                  AgendamentoRepositoryPort agendamentoRepository) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.agendamentoRepository = agendamentoRepository;
    }

    @PostMapping
    @Operation(summary = "Criar um novo agendamento")
    public ResponseEntity<AgendamentoResponse> criar(@Valid @RequestBody CriarAgendamentoRequest request) {
        var agendamento = criarAgendamentoUseCase.executar(
                request.usuarioId(), request.servicoId(), request.profissionalId(), request.dataHoraInicio());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(agendamento));
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar agendamentos de um usuário")
    public ResponseEntity<List<AgendamentoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        var agendamentos = agendamentoRepository.buscarPorUsuarioId(usuarioId);
        var response = agendamentos.stream().map(this::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar um agendamento")
    public ResponseEntity<AgendamentoResponse> cancelar(@PathVariable Long id,
                                                         @Valid @RequestBody CancelarAgendamentoRequest request) {
        var agendamento = agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        agendamento.cancelar(request.motivo());
        var salvo = agendamentoRepository.salvar(agendamento);
        return ResponseEntity.ok(toResponse(salvo));
    }

    @PatchMapping("/{id}/confirmar")
    @Operation(summary = "Confirmar um agendamento")
    public ResponseEntity<AgendamentoResponse> confirmar(@PathVariable Long id) {
        var agendamento = agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        agendamento.confirmar();
        var salvo = agendamentoRepository.salvar(agendamento);
        return ResponseEntity.ok(toResponse(salvo));
    }

    @PatchMapping("/{id}/finalizar")
    @Operation(summary = "Finalizar um agendamento")
    public ResponseEntity<AgendamentoResponse> finalizar(@PathVariable Long id) {
        var agendamento = agendamentoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));
        agendamento.finalizar();
        var salvo = agendamentoRepository.salvar(agendamento);
        return ResponseEntity.ok(toResponse(salvo));
    }

    private AgendamentoResponse toResponse(Agendamento a) {
        return new AgendamentoResponse(
                a.getId(), a.getUsuarioId(), null,
                a.getServicoId(), null,
                a.getProfissionalId(), null,
                a.getDataHoraInicio(), a.getDataHoraFim(),
                a.getStatus().name(), a.getMotivoCancelamento());
    }

}
