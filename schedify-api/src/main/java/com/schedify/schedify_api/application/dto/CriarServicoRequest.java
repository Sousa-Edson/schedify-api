package com.schedify.schedify_api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CriarServicoRequest {

    @NotBlank(message = "Nome do serviço é obrigatório")
    private String nome;

    @Positive(message = "Duração deve ser maior que zero")
    private Integer duracaoMinutos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}
