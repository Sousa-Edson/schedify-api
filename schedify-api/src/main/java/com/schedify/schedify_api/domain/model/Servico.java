package com.schedify.schedify_api.domain.model;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class Servico {

    private Long id;
    private String nome;
    private int duracaoMinutos;
    private int bufferMinutos;
    private BigDecimal valor;
    private Set<Profissional> profissionais = new LinkedHashSet<>();

    public Servico() {}

    public Servico(String nome, int duracaoMinutos, BigDecimal valor) {
        setNome(nome);
        setDuracaoMinutos(duracaoMinutos);
        setValor(valor);
        this.bufferMinutos = 0;
    }

    public Servico(String nome, int duracaoMinutos, int bufferMinutos, BigDecimal valor) {
        setNome(nome);
        setDuracaoMinutos(duracaoMinutos);
        setBufferMinutos(bufferMinutos);
        setValor(valor);
    }

    public Servico(Long id, String nome, int duracaoMinutos, int bufferMinutos, BigDecimal valor) {
        this.id = id;
        setNome(nome);
        setDuracaoMinutos(duracaoMinutos);
        setBufferMinutos(bufferMinutos);
        setValor(valor);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public int getBufferMinutos() { return bufferMinutos; }
    public BigDecimal getValor() { return valor; }
    public int getTotalMinutos() { return duracaoMinutos + bufferMinutos; }
    public Set<Profissional> getProfissionais() { return profissionais; }

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

    public void setBufferMinutos(int bufferMinutos) {
        if (bufferMinutos < 0)
            throw new IllegalArgumentException("Buffer não pode ser negativo");
        this.bufferMinutos = bufferMinutos;
    }

    public void setValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Valor do serviço deve ser maior que zero");
        this.valor = valor;
    }

    public void adicionarProfissional(Profissional profissional) {
        if (profissional == null)
            throw new IllegalArgumentException("Profissional não pode ser nulo");
        this.profissionais.add(profissional);
        profissional.adicionarServico(this);
    }

    public void removerProfissional(Profissional profissional) {
        this.profissionais.remove(profissional);
        profissional.removerServico(this);
    }

}
