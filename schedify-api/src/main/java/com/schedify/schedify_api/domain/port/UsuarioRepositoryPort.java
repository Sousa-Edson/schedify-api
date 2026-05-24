package com.schedify.schedify_api.domain.port;

import com.schedify.schedify_api.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
}
