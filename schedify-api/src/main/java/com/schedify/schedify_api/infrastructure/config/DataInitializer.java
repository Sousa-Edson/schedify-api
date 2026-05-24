package com.schedify.schedify_api.infrastructure.config;

import com.schedify.schedify_api.domain.model.Disponibilidade;
import com.schedify.schedify_api.domain.model.Profissional;
import com.schedify.schedify_api.domain.port.DisponibilidadeRepositoryPort;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
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

    private final ProfissionalRepositoryPort profissionalRepository;
    private final DisponibilidadeRepositoryPort disponibilidadeRepository;

    public DataInitializer(ProfissionalRepositoryPort profissionalRepository,
                            DisponibilidadeRepositoryPort disponibilidadeRepository) {
        this.profissionalRepository = profissionalRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
    }

    @Override
    public void run(String... args) {
        var profissional = criarProfissionalPadrao();
        if (profissional == null) return;
        criarDisponibilidadesPadrao(profissional.getId());
    }

    private Profissional criarProfissionalPadrao() {
        var existentes = profissionalRepository.buscarPorId(1L);
        if (existentes.isPresent()) {
            log.info("Profissional padrão já existe, pulando inicialização");
            return null;
        }
        log.info("Criando profissional padrão");
        return profissionalRepository.salvar(new Profissional("Profissional Padrão"));
    }

    private void criarDisponibilidadesPadrao(Long profissionalId) {
        log.info("Inicializando disponibilidades padrão (seg-sex 09:00-18:00)");
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(18, 0), profissionalId));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.TUESDAY, LocalTime.of(9, 0), LocalTime.of(18, 0), profissionalId));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.WEDNESDAY, LocalTime.of(9, 0), LocalTime.of(18, 0), profissionalId));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.THURSDAY, LocalTime.of(9, 0), LocalTime.of(18, 0), profissionalId));
        disponibilidadeRepository.salvar(new Disponibilidade(DayOfWeek.FRIDAY, LocalTime.of(9, 0), LocalTime.of(18, 0), profissionalId));
    }

}
