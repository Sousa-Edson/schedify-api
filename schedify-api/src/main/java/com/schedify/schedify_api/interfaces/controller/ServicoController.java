package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.dto.CriarServicoRequest;
import com.schedify.schedify_api.application.dto.ServicoDTO;
import com.schedify.schedify_api.application.dto.mapper.ServicoMapper;
import com.schedify.schedify_api.application.usecase.CriarServicoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Operações relacionadas a serviços")
public class ServicoController {

    private final CriarServicoUseCase criarServicoUseCase;
    private final ServicoMapper servicoMapper;

    public ServicoController(CriarServicoUseCase criarServicoUseCase,
                              ServicoMapper servicoMapper) {
        this.criarServicoUseCase = criarServicoUseCase;
        this.servicoMapper = servicoMapper;
    }

    @PostMapping
    @Operation(summary = "Criar um novo serviço")
    @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso")
    public ResponseEntity<ServicoDTO> criar(@RequestBody @Valid CriarServicoRequest request) {
        var servico = criarServicoUseCase.executar(request.getNome(), request.getDuracaoMinutos());
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoMapper.toDTO(servico));
    }
}
