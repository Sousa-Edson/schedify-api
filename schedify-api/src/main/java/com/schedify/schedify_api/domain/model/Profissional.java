package com.schedify.schedify_api.domain.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Profissional {

    private Long id;
    private String nome;
    private boolean ativo = true;
    private Set<Servico> servicos = new LinkedHashSet<>();

    public Profissional() {}

    public Profissional(String nome) {
        setNome(nome);
    }

    public Profissional(Long id, String nome) {
        this.id = id;
        setNome(nome);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public boolean isAtivo() { return ativo; }
    public Set<Servico> getServicos() { return Collections.unmodifiableSet(servicos); }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isBlank())
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        if (nome.trim().length() > 150)
            throw new IllegalArgumentException("Nome do profissional deve ter no máximo 150 caracteres");
        this.nome = nome.trim();
    }

    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public void ativar() { this.ativo = true; }
    public void desativar() { this.ativo = false; }

    public boolean prestaServico(Servico servico) {
        return servicos.contains(servico);
    }

}
