package com.schedify.schedify_api.application.dto;

import jakarta.validation.constraints.NotBlank;

public class CriarUsuarioRequest {

    @NotBlank(message = "Nome do usuário é obrigatório")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
