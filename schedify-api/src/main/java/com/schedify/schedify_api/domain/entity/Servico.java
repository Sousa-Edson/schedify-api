package com.schedify.schedify_api.domain.entity;

public class Servico {

    private Long id;
    private String nome;
    private Integer duracaoMinutos;

    public Servico() {
    }

    public Servico(String nome, Integer duracaoMinutos) {
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
    }

    public Servico(Long id, String nome, Integer duracaoMinutos) {
        this.id = id;
        this.nome = nome;
        this.duracaoMinutos = duracaoMinutos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
