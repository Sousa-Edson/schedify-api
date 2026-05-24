package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.entity.Servico;
import com.schedify.schedify_api.domain.repository.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarServicoUseCase {

    private final ServicoRepository servicoRepository;

    public CriarServicoUseCase(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Transactional
    public Servico executar(String nome, Integer duracaoMinutos) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do serviço é obrigatório");
        }
        if (duracaoMinutos == null || duracaoMinutos <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
        var servico = new Servico(nome.trim(), duracaoMinutos);
        return servicoRepository.salvar(servico);
    }
}
