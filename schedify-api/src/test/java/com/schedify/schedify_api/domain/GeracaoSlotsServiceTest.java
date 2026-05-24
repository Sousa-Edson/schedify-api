package com.schedify.schedify_api.domain;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.model.Disponibilidade;
import com.schedify.schedify_api.domain.model.Servico;
import com.schedify.schedify_api.domain.service.GeracaoSlotsService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeracaoSlotsServiceTest {

    private GeracaoSlotsService service;
    private Servico servico;
    private List<Disponibilidade> disponibilidades;

    @BeforeEach
    void setUp() {
        service = new GeracaoSlotsService();
        servico = new Servico(1L, "Corte", 30);
        disponibilidades = List.of(
                new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(12, 0), 1L));
    }

    @Test
    void deveGerarSlotsNoIntervaloCorreto() {
        var data = LocalDate.of(2026, 6, 1);
        var slots = service.gerarSlots(data, servico, disponibilidades, List.of(), 15);
        assertEquals(11, slots.size());
        assertEquals(LocalDateTime.of(2026, 6, 1, 9, 0), slots.getFirst().inicio());
        assertEquals(LocalDateTime.of(2026, 6, 1, 11, 30), slots.getLast().inicio());
    }

    @Test
    void deveRemoverSlotsOcupados() {
        var data = LocalDate.of(2026, 6, 1);
        var ocupados = List.of(
                new Agendamento(1L, 1L, 1L,
                        LocalDateTime.of(2026, 6, 1, 9, 0),
                        LocalDateTime.of(2026, 6, 1, 10, 0)));
        var slots = service.gerarSlots(data, servico, disponibilidades, ocupados, 15);
        assertEquals(7, slots.size());
        assertTrue(slots.stream().noneMatch(s -> s.inicio().equals(LocalDateTime.of(2026, 6, 1, 9, 0))));
        assertTrue(slots.stream().anyMatch(s -> s.inicio().equals(LocalDateTime.of(2026, 6, 1, 10, 0))));
    }

    @Test
    void deveRetornarListaVaziaQuandoDisponibilidadeVazia() {
        var data = LocalDate.of(2026, 6, 1);
        var slots = service.gerarSlots(data, servico, List.of(), List.of(), 15);
        assertTrue(slots.isEmpty());
    }

    @Test
    void deveRespeitarIntervaloConfigurado() {
        var data = LocalDate.of(2026, 6, 1);
        var slots = service.gerarSlots(data, servico, disponibilidades, List.of(), 30);
        assertEquals(6, slots.size());
    }

}
