package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.model.Profissional;
import com.schedify.schedify_api.domain.port.ProfissionalRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarProfissionalUseCase {

    private final ProfissionalRepositoryPort profissionalRepository;

    public CadastrarProfissionalUseCase(ProfissionalRepositoryPort profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    @Transactional
    public Profissional executar(String nome) {
        var profissional = new Profissional(nome);
        return profissionalRepository.salvar(profissional);
    }

}
