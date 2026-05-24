package com.schedify.schedify_api.infrastructure.config;

import com.schedify.schedify_api.domain.entity.Disponibilidade;
import com.schedify.schedify_api.domain.repository.DisponibilidadeRepository;
import java.time.DayOfWeek;
import java.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod & !test")
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final DisponibilidadeRepository disponibilidadeRepository;

    public DataInitializer(DisponibilidadeRepository disponibilidadeRepository) {
        this.disponibilidadeRepository = disponibilidadeRepository;
    }

    @Override
    public void run(String... args) {
        var existentes = disponibilidadeRepository.buscarPorDiaSemana(DayOfWeek.MONDAY);
        if (!existentes.isEmpty()) {
            log.info("Disponibilidades já existem, pulando inicialização");
            return;
        }

        log.info("Inicializando disponibilidades padrão (seg-sex 09:00-18:00)");
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(18, 0)));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(18, 0)));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(18, 0)));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.THURSDAY, LocalTime.of(9, 0), LocalTime.of(18, 0)));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(18, 0)));
    }
}
