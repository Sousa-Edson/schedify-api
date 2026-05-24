package com.schedify.schedify_api.domain.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Servico {

    private Long id;
    private String nome;
    private int duracaoMinutos;
    private Set<Profissional> profissionais = new LinkedHashSet<>();

    public Servico() {}

    public Servico(String nome, int duracaoMinutos) {
        setNome(nome);
        setDuracaoMinutos(duracaoMinutos);
    }

    public Servico(Long id, String nome, int duracaoMinutos) {
        this.id = id;
        setNome(nome);
        setDuracaoMinutos(duracaoMinutos);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public Set<Profissional> getProfissionais() { return Collections.unmodifiableSet(profissionais); }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isBlank())
            throw new IllegalArgumentException("Nome do serviço é obrigatório");
        if (nome.trim().length() > 150)
            throw new IllegalArgumentException("Nome do serviço deve ter no máximo 150 caracteres");
        this.nome = nome.trim();
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        if (duracaoMinutos <= 0)
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        this.duracaoMinutos = duracaoMinutos;
    }

    public void adicionarProfissional(Profissional profissional) {
        if (profissional == null)
            throw new IllegalArgumentException("Profissional não pode ser nulo");
        this.profissionais.add(profissional);
        profissional.getServicos().add(this);
    }

    public void removerProfissional(Profissional profissional) {
        this.profissionais.remove(profissional);
        profissional.getServicos().remove(this);
    }

}
