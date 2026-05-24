package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.dto.SlotDTO;
import com.schedify.schedify_api.application.usecase.GerarSlotsDisponiveisUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slots")
@Tag(name = "Slots", description = "Consulta de horários disponíveis para agendamento")
public class SlotController {

    private final GerarSlotsDisponiveisUseCase gerarSlotsDisponiveisUseCase;

    public SlotController(GerarSlotsDisponiveisUseCase gerarSlotsDisponiveisUseCase) {
        this.gerarSlotsDisponiveisUseCase = gerarSlotsDisponiveisUseCase;
    }

    @GetMapping
    @Operation(summary = "Listar slots disponíveis para uma data e serviço")
    public ResponseEntity<List<SlotDTO>> listarSlotsDisponiveis(
            @Parameter(description = "Data no formato YYYY-MM-DD")
            @RequestParam("data") LocalDate data,
            @Parameter(description = "ID do serviço")
            @RequestParam("servicoId") Long servicoId) {
        var slots = gerarSlotsDisponiveisUseCase.executar(data, servicoId);
        return ResponseEntity.ok(slots);
    }
}
