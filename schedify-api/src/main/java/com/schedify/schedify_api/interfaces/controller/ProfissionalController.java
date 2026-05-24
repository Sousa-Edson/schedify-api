package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.usecase.CadastrarProfissionalUseCase;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import com.schedify.schedify_api.interfaces.dto.CriarProfissionalRequest;
import com.schedify.schedify_api.interfaces.dto.ProfissionalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profissionais")
@Tag(name = "Profissionais", description = "Operações relacionadas a profissionais")
public class ProfissionalController {

    private final CadastrarProfissionalUseCase cadastrarProfissionalUseCase;
    private final ProfissionalRepositoryPort profissionalRepository;

    public ProfissionalController(CadastrarProfissionalUseCase cadastrarProfissionalUseCase,
                                   ProfissionalRepositoryPort profissionalRepository) {
        this.cadastrarProfissionalUseCase = cadastrarProfissionalUseCase;
        this.profissionalRepository = profissionalRepository;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo profissional")
    public ResponseEntity<ProfissionalResponse> cadastrar(@Valid @RequestBody CriarProfissionalRequest request) {
        var profissional = cadastrarProfissionalUseCase.executar(request.nome());
        var response = toResponse(profissional);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar profissional por ID")
    public ResponseEntity<ProfissionalResponse> buscar(@PathVariable Long id) {
        var profissional = profissionalRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));
        return ResponseEntity.ok(toResponse(profissional));
    }

    private ProfissionalResponse toResponse(com.schedify.schedify_api.domain.model.Profissional profissional) {
        var servicoIds = profissional.getServicos().stream()
                .map(com.schedify.schedify_api.domain.model.Servico::getId)
                .collect(Collectors.toSet());
        return new ProfissionalResponse(profissional.getId(), profissional.getNome(), servicoIds);
    }

}
