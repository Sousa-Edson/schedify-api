package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.domain.model.Bloqueio;
import com.schedify.schedify_api.domain.port.BloqueioRepositoryPort;
import com.schedify.schedify_api.interfaces.dto.BloqueioResponse;
import com.schedify.schedify_api.interfaces.dto.CriarBloqueioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bloqueios")
@Tag(name = "Bloqueios", description = "Operações relacionadas a bloqueios de agenda")
public class BloqueioController {

    private final BloqueioRepositoryPort bloqueioRepository;

    public BloqueioController(BloqueioRepositoryPort bloqueioRepository) {
        this.bloqueioRepository = bloqueioRepository;
    }

    @PostMapping
    @Operation(summary = "Criar um bloqueio na agenda do profissional")
    public ResponseEntity<BloqueioResponse> criar(@Valid @RequestBody CriarBloqueioRequest request) {
        var bloqueio = new Bloqueio(request.profissionalId(), request.dataInicio(), request.dataFim(), request.motivo());
        var salvo = bloqueioRepository.salvar(bloqueio);
        var response = new BloqueioResponse(
                salvo.getId(), salvo.getProfissionalId(),
                salvo.getDataInicio(), salvo.getDataFim(), salvo.getMotivo());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
