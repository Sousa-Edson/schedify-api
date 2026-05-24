package com.schedify.schedify_api.domain;

import com.schedify.schedify_api.domain.model.Agendamento;
import com.schedify.schedify_api.domain.service.ValidacaoConflitoService;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidacaoConflitoServiceTest {

    private ValidacaoConflitoService service;
    private Agendamento existente;

    @BeforeEach
    void setUp() {
        service = new ValidacaoConflitoService();
        existente = new Agendamento(1L, 1L, 1L,
                LocalDateTime.of(2026, 6, 1, 10, 0),
                LocalDateTime.of(2026, 6, 1, 11, 0));
    }

    @Test
    void deveDetectarConflitoQuandoSobreposto() {
        var novo = new Agendamento(2L, 1L, 1L,
                LocalDateTime.of(2026, 6, 1, 10, 30),
                LocalDateTime.of(2026, 6, 1, 11, 30));
        assertTrue(service.temConflito(novo, List.of(existente)));
    }

    @Test
    void deveDetectarConflitoQuandoNovoComecaAntesETerminaDurante() {
        var novo = new Agendamento(2L, 1L, 1L,
                LocalDateTime.of(2026, 6, 1, 9, 30),
                LocalDateTime.of(2026, 6, 1, 10, 30));
        assertTrue(service.temConflito(novo, List.of(existente)));
    }

    @Test
    void deveDetectarConflitoQuandoNovoContemExistente() {
        var novo = new Agendamento(2L, 1L, 1L,
                LocalDateTime.of(2026, 6, 1, 9, 0),
                LocalDateTime.of(2026, 6, 1, 12, 0));
        assertTrue(service.temConflito(novo, List.of(existente)));
    }

    @Test
    void naoDeveDetectarConflitoQuandoAntes() {
        var novo = new Agendamento(2L, 1L, 1L,
                LocalDateTime.of(2026, 6, 1, 8, 0),
                LocalDateTime.of(2026, 6, 1, 9, 0));
        assertFalse(service.temConflito(novo, List.of(existente)));
    }

    @Test
    void naoDeveDetectarConflitoQuandoDepois() {
        var novo = new Agendamento(2L, 1L, 1L,
                LocalDateTime.of(2026, 6, 1, 11, 0),
                LocalDateTime.of(2026, 6, 1, 12, 0));
        assertFalse(service.temConflito(novo, List.of(existente)));
    }

    @Test
    void naoDeveDetectarConflitoQuandoExatamenteNoFim() {
        var novo = new Agendamento(2L, 1L, 1L,
                LocalDateTime.of(2026, 6, 1, 11, 0),
                LocalDateTime.of(2026, 6, 1, 11, 30));
        assertFalse(service.temConflito(novo, List.of(existente)));
    }

    @Test
    void deveLancarExcecaoQuandoDatasInvalidas() {
        assertThrows(IllegalArgumentException.class,
                () -> new Agendamento(2L, 1L, 1L, null, null));
    }

}
