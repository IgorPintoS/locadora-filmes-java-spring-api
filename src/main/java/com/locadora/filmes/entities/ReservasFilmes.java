package com.locadora.filmes.entities;

import jakarta.persistence.*;

@Entity
public class ReservasFilmes {

    @Id
    @Column(name = "reserva_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Id reservaId;

    @Column(name = "reserva_dias")
    private Integer diasReserva;

    @Column(name = "taxa_locacao")
    private Double taxaLocacao;

    @Column(name = "preco_locacao")
    private Double precoLocacao;

    @JoinColumn(name = "filme_id")
    @OneToOne
    private Id IdFilme;

    @JoinColumn(name = "cliente_id")
    @OneToMany
    private Id idCliente;

    @Column(name = "reservado")
    private Character reservado;

    public ReservasFilmes() {
    }

    public ReservasFilmes(Id idFilme, Id idCliente, Double precoLocacao, Double taxaLocacao, Integer diasReserva, Character reservado) {
        IdFilme = idFilme;
        idCliente = idCliente;
        this.precoLocacao = precoLocacao;
        this.taxaLocacao = taxaLocacao;
        this.diasReserva = diasReserva;
        this.reservado = reservado;
    }

    public Id getIdFilme() {
        return IdFilme;
    }

    public void setIdFilme(Id idFilme) {
        IdFilme = idFilme;
    }

    public Id getIdCliente() {
        return idCliente;

    }

    public void setIdCliente(Id idCliente) {
        this.idCliente = idCliente;
    }

    public Double getPrecoLocacao() {
        return precoLocacao;
    }

    public void setPrecoLocacao(Double precoLocacao) {
        this.precoLocacao = precoLocacao;
    }

    public Double getTaxaLocacao() {
        return taxaLocacao;
    }

    public void setTaxaLocacao(Double taxaLocacao) {
        this.taxaLocacao = taxaLocacao;
    }

    public Integer getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(Integer diasReserva) {
        this.diasReserva = diasReserva;
    }

    public Id getReservaId() {
        return reservaId;
    }

    public Character getReservado() {
        return reservado;
    }

    public void setReservado(Character reservado) {
        this.reservado = reservado;
    }
}
