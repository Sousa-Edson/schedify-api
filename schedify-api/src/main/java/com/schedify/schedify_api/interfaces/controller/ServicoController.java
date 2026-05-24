package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.usecase.CriarServicoUseCase;
import com.schedify.schedify_api.interfaces.dto.CriarServicoRequest;
import com.schedify.schedify_api.interfaces.dto.ServicoResponse;
import com.schedify.schedify_api.interfaces.dto.VincularProfissionalRequest;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Operações relacionadas a serviços")
public class ServicoController {

    private final CriarServicoUseCase criarServicoUseCase;
    private final ServicoRepositoryPort servicoRepository;
    private final ProfissionalRepositoryPort profissionalRepository;

    public ServicoController(CriarServicoUseCase criarServicoUseCase,
                              ServicoRepositoryPort servicoRepository,
                              ProfissionalRepositoryPort profissionalRepository) {
        this.criarServicoUseCase = criarServicoUseCase;
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @PostMapping
    @Operation(summary = "Criar um novo serviço")
    public ResponseEntity<ServicoResponse> criar(@Valid @RequestBody CriarServicoRequest request) {
        var servico = criarServicoUseCase.executar(request.nome(), request.duracaoMinutos());
        var response = toResponse(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{servicoId}/profissionais")
    @Operation(summary = "Vincular um profissional a um serviço")
    public ResponseEntity<ServicoResponse> vincularProfissional(
            @PathVariable Long servicoId,
            @Valid @RequestBody VincularProfissionalRequest request) {
        var servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
        var profissional = profissionalRepository.buscarPorId(request.profissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));
        servico.adicionarProfissional(profissional);
        servico = servicoRepository.salvar(servico);
        return ResponseEntity.ok(toResponse(servico));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar serviço por ID")
    public ResponseEntity<ServicoResponse> buscar(@PathVariable Long id) {
        var servico = servicoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
        return ResponseEntity.ok(toResponse(servico));
    }

    private ServicoResponse toResponse(com.schedify.schedify_api.domain.model.Servico servico) {
        var profissionalIds = servico.getProfissionais().stream()
                .map(com.schedify.schedify_api.domain.model.Profissional::getId)
                .collect(Collectors.toSet());
        return new ServicoResponse(servico.getId(), servico.getNome(), servico.getDuracaoMinutos(), profissionalIds);
    }

}
