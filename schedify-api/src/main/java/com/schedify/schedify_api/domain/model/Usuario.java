package com.schedify.schedify_api.domain.model;

public class Usuario {

    private Long id;
    private String nome;

    public Usuario() {}

    public Usuario(String nome) {
        setNome(nome);
    }

    public Usuario(Long id, String nome) {
        this.id = id;
        setNome(nome);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isBlank())
            throw new IllegalArgumentException("Nome do usuário é obrigatório");
        if (nome.trim().length() > 150)
            throw new IllegalArgumentException("Nome do usuário deve ter no máximo 150 caracteres");
        this.nome = nome.trim();
    }

}
