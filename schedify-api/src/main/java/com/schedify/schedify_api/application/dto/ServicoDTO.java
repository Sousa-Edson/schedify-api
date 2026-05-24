package com.schedify.schedify_api.application.dto;

public class ServicoDTO {

    private Long id;
    private String nome;
    private Integer duracaoMinutos;

    public ServicoDTO() {
    }

    public ServicoDTO(Long id, String nome, Integer duracaoMinutos) {
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
