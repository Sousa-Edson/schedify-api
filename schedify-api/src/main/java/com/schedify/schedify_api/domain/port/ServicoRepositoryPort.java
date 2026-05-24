package com.schedify.schedify_api.domain.port;

import com.schedify.schedify_api.domain.model.Servico;
import java.util.Optional;

public interface ServicoRepositoryPort {
    Servico salvar(Servico servico);
    Optional<Servico> buscarPorId(Long id);
}
