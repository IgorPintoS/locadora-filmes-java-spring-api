package com.locadora.filmes.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ReservaFilme {

    @Id
    @Column(name = "id_reserva")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idReserva;

    @Column(name = "reserva_dias")
    private Integer diasReserva;

    @Column(name = "taxa_locacao")
    private Double taxaLocacao;

    @Column(name = "preco_locacao")
    private Double precoLocacao;

    @JoinColumn(name = "filme_id")
    private Long idFilme;

    @JoinColumn(name = "cliente_id")
    private Long idCliente;

    @Column(name = "reservado")
    private Character reservado;

    @Column(name = "data_locacao")
    private LocalDateTime dataLocacao;

    @Column(name = "data_devolucao")
    private LocalDateTime dataDevolucaoLocacao;

    @Column(name = "status_locacao")
    private char statusLocacao;

    public ReservaFilme() {
    }

    public ReservaFilme(Long idFilme, Long idCliente, Double precoLocacao, Double taxaLocacao, Integer diasReserva, Character reservado, LocalDateTime dataLocacao, LocalDateTime dataDevolucaoLocacao, char statusLocacao) {
        this.idCliente = idCliente;
        this.idFilme = idFilme;
        this.precoLocacao = precoLocacao;
        this.taxaLocacao = taxaLocacao;
        this.diasReserva = diasReserva;
        this.reservado = reservado;
        this.dataLocacao = dataLocacao;
        this.dataDevolucaoLocacao = dataDevolucaoLocacao;
        this.statusLocacao = statusLocacao;
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

    public Long getIdReserva() {
        return idReserva;
    }

    public Character getReservado() {
        return reservado;
    }

    public void setReservado(Character reservado) {
        this.reservado = reservado;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDateTime getDataDevolucaoLocacao() {
        return dataDevolucaoLocacao;
    }

    public void setDataDevolucaoLocacao(LocalDateTime dataDevolucaoLocacao) {
        this.dataDevolucaoLocacao = dataDevolucaoLocacao;
    }

    public char getStatusLocacao() {
        return statusLocacao;
    }

    public void setStatusLocacao(char statusLocacao) {
        this.statusLocacao = statusLocacao;
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;

    }
}
