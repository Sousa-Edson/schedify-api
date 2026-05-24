package com.schedify.schedify_api.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profissionais")
public class ProfissionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @ManyToMany(mappedBy = "profissionais")
    private Set<ServicoEntity> servicos = new HashSet<>();

    public ProfissionalEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Set<ServicoEntity> getServicos() { return servicos; }
    public void setServicos(Set<ServicoEntity> servicos) { this.servicos = servicos; }

}
