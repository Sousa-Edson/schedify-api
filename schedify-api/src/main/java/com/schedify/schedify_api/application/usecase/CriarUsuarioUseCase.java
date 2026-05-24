package com.schedify.schedify_api.application.usecase;

import com.schedify.schedify_api.domain.entity.Usuario;
import com.schedify.schedify_api.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public CriarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario executar(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        }
        var usuario = new Usuario(nome.trim());
        return usuarioRepository.salvar(usuario);
    }
}
