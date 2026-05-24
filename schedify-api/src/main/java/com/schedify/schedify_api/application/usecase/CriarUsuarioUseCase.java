package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.model.Usuario;
import com.schedify.schedify_api.domain.port.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;

    public CriarUsuarioUseCase(UsuarioRepositoryPort usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario executar(String nome) {
        var usuario = new Usuario(nome);
        return usuarioRepository.salvar(usuario);
    }

}
