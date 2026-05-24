package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.model.Servico;
import com.schedify.schedify_api.domain.port.ServicoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarServicoUseCase {

    private final ServicoRepositoryPort servicoRepository;

    public CriarServicoUseCase(ServicoRepositoryPort servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public Servico executar(String nome, int duracaoMinutos) {
        var servico = new Servico(nome, duracaoMinutos);
        return servicoRepository.salvar(servico);
    }

}
