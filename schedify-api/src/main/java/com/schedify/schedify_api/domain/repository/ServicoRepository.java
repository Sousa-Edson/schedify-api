package com.schedify.schedify_api.domain.repository;

import com.schedify.schedify_api.domain.entity.Servico;
import java.util.Optional;

public interface ServicoRepository {
    Servico salvar(Servico servico);
    Optional<Servico> buscarPorId(Long id);
}
