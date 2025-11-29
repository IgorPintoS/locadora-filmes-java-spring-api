package com.locadora.filmes.entities;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCliente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "filmes_locados_mes")
    private Integer filmesLocados;

    public Cliente() {
    }

    public Cliente(String nome, String sobrenome, Integer idade, String endereco, Integer numero, String bairro, String cpfCnpj, Integer filmesLocados) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cpfCnpj = cpfCnpj;
        this.filmesLocados = filmesLocados;
    }

    public Long getIdCliente(){
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getFilmesLocados() {
        return filmesLocados;
    }

    public void setFilmesLocados(Integer filmesLocados) {
        this.filmesLocados = filmesLocados;
    }
}


