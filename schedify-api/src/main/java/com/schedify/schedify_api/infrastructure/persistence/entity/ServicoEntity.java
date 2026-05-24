package com.schedify.schedify_api.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "servicos")
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private Integer duracaoMinutos;

    @ManyToMany
    @JoinTable(
        name = "servico_profissional",
        joinColumns = @JoinColumn(name = "servico_id"),
        inverseJoinColumns = @JoinColumn(name = "profissional_id")
    )
    private Set<ProfissionalEntity> profissionais = new HashSet<>();

    public ServicoEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public Set<ProfissionalEntity> getProfissionais() { return profissionais; }
    public void setProfissionais(Set<ProfissionalEntity> profissionais) { this.profissionais = profissionais; }

}
