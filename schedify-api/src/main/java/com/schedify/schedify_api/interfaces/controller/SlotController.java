package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.usecase.ListarSlotsDisponiveisUseCase;
import com.schedify.schedify_api.domain.service.AgendaService;
import com.schedify.schedify_api.domain.service.AgendaService.Slot;
import com.schedify.schedify_api.interfaces.dto.SlotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slots")
@Tag(name = "Slots", description = "Geração de horários disponíveis")
public class SlotController {

    private final ListarSlotsDisponiveisUseCase listarSlotsDisponiveisUseCase;
    private final AgendaService agendaService;

    public SlotController(ListarSlotsDisponiveisUseCase listarSlotsDisponiveisUseCase,
                           AgendaService agendaService) {
        this.listarSlotsDisponiveisUseCase = listarSlotsDisponiveisUseCase;
        this.agendaService = agendaService;
    }

    @GetMapping
    @Operation(summary = "Listar slots disponíveis para um serviço e profissional")
    public ResponseEntity<List<SlotResponse>> listarSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam Long servicoId,
            @RequestParam Long profissionalId,
            @RequestParam(defaultValue = "false") boolean melhor) {
        var slots = listarSlotsDisponiveisUseCase.executar(data, servicoId, profissionalId);

        if (melhor && !slots.isEmpty()) {
            var melhorSlot = agendaService.sugerirMelhorHorario(slots, List.of());
            return ResponseEntity.ok(melhorSlot != null
                    ? List.of(new SlotResponse(melhorSlot.inicio(), melhorSlot.fim()))
                    : List.of());
        }

        var response = slots.stream()
                .map(s -> new SlotResponse(s.inicio(), s.fim()))
                .toList();
        return ResponseEntity.ok(response);
    }

}
