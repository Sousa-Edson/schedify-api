package com.schedify.schedify_api.domain.port;

import com.schedify.schedify_api.domain.model.Profissional;
import java.util.Optional;

public interface ProfissionalRepositoryPort {
    Profissional salvar(Profissional profissional);
    Optional<Profissional> buscarPorId(Long id);
}
