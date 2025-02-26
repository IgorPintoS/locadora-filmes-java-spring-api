package com.locadora.filmes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @Column(name = "filme_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Id idFilme;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "duracao_min")
    private Double duracaoMin;

    @Column(name = "genero")
    private String genero;

    @Column(name = "diretor")
    private String diretor;

    @Column(name = "faixa_etaria")
    private Integer faixaEtaria;

    @Column(name = "secao")
    private String secao;

    @Column(name = "prateleira")
    private String prateleira;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    public Filme() {
    }

    public Filme(String titulo, String genero, String diretor, Double duracaoMin, Integer faixaEtaria, String secao, String prateleira, Integer quantidadeEstoque) {
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.duracaoMin = duracaoMin;
        this.faixaEtaria = faixaEtaria;
        this.secao = secao;
        this.prateleira = prateleira;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Id getIdFilme() {
        return idFilme;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public Integer getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(Integer faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(Double duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
