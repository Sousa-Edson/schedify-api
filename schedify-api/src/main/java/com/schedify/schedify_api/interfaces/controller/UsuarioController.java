package com.schedify.schedify_api.interfaces.controller;

import com.schedify.schedify_api.application.dto.CriarUsuarioRequest;
import com.schedify.schedify_api.application.dto.UsuarioDTO;
import com.schedify.schedify_api.application.dto.mapper.UsuarioMapper;
import com.schedify.schedify_api.application.usecase.CriarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase,
                              UsuarioMapper usuarioMapper) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.usuarioMapper = usuarioMapper;
    }

    @PostMapping
    @Operation(summary = "Criar um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    public ResponseEntity<UsuarioDTO> criar(@RequestBody @Valid CriarUsuarioRequest request) {
        var usuario = criarUsuarioUseCase.executar(request.getNome());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioMapper.toDTO(usuario));
    }
}
