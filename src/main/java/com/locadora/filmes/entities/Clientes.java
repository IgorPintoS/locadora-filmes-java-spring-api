package com.locadora.filmes.entities;

import jakarta.persistence.*;

@Entity
public class Clientes {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Id idCliente;

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
    private String CpfCnpj;

    @Column(name = "filmes_locados_mes")
    private Integer filmesLocadosMes;

    @Column(name = "filmes_reservados_mes")
    private Integer filmesReservadosMes;
}
