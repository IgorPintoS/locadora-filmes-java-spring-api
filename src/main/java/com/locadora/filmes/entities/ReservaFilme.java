package com.locadora.filmes.entities;

import jakarta.persistence.*;

@Entity
public class ReservaFilme {

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
    private Filme idFilme;

    @JoinColumn(name = "cliente_id")
    @OneToMany
    private Cliente idCliente;

    @Column(name = "reservado")
    private Character reservado;

    public ReservaFilme() {
    }

    public ReservaFilme(Id idFilme, Id idCliente, Double precoLocacao, Double taxaLocacao, Integer diasReserva, Character reservado) {
        idCliente = idCliente;
        this.precoLocacao = precoLocacao;
        this.taxaLocacao = taxaLocacao;
        this.diasReserva = diasReserva;
        this.reservado = reservado;
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
