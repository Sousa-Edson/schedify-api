package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.dto.AgendamentoDTO;
import com.schedify.schedify_api.application.dto.CriarAgendamentoRequest;
import com.schedify.schedify_api.application.dto.mapper.AgendamentoMapper;
import com.schedify.schedify_api.application.usecase.CriarAgendamentoUseCase;
import com.schedify.schedify_api.domain.entity.Agendamento;
import com.schedify.schedify_api.domain.repository.AgendamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Operações relacionadas a agendamentos")
public class AgendamentoController {

    private final CriarAgendamentoUseCase criarAgendamentoUseCase;
    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoController(CriarAgendamentoUseCase criarAgendamentoUseCase,
                                  AgendamentoRepository agendamentoRepository,
                                  AgendamentoMapper agendamentoMapper) {
        this.criarAgendamentoUseCase = criarAgendamentoUseCase;
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    @PostMapping
    @Operation(summary = "Criar um novo agendamento")
    @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso")
    @ApiResponse(responseCode = "409", description = "Conflito de horário com agendamento existente")
    public ResponseEntity<AgendamentoDTO> criar(@RequestBody @Valid CriarAgendamentoRequest request) {
        var agendamento = criarAgendamentoUseCase.executar(
                request.getUsuarioId(),
                request.getServicoId(),
                request.getDataHoraInicio());
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(agendamento));
    }

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Listar agendamentos de um usuário")
    public ResponseEntity<List<AgendamentoDTO>> listarPorUsuario(
            @PathVariable Long id) {
        var agendamentos = agendamentoRepository.buscarPorUsuarioId(id);
        var dtos = agendamentos.stream().map(this::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }

    private AgendamentoDTO toDTO(Agendamento agendamento) {
        return agendamentoMapper.toDTO(agendamento);
    }
}
