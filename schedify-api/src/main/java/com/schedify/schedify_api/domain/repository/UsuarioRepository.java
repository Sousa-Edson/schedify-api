package com.schedify.schedify_api.domain.repository;

import com.schedify.schedify_api.domain.entity.Usuario;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
}
