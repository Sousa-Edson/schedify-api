package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.usecase.CriarUsuarioUseCase;
import com.schedify.schedify_api.interfaces.dto.CriarUsuarioRequest;
import com.schedify.schedify_api.interfaces.dto.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
    }

    @PostMapping
    @Operation(summary = "Criar um novo usuário")
    public ResponseEntity<UsuarioResponse> criar(@Valid @RequestBody CriarUsuarioRequest request) {
        var usuario = criarUsuarioUseCase.executar(request.nome());
        var response = new UsuarioResponse(usuario.getId(), usuario.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
