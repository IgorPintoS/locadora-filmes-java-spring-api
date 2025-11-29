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
    @ManyToOne
    private Filme filme;

    @JoinColumn(name = "cliente_id")
    @ManyToMany
    private Cliente cliente;

    @Column(name = "reservado")
    private Character reservado;

    @Column(name = "data_locacao")
    private LocalDateTime dataLocacao;

    @Column(name = "data_devolucao")
    private LocalDateTime dataDevolucaoLocacao;

    @Column(name = "status_locacao")
    private char statusLocacao;

    @Column(name = "filme_devolvido")
    private boolean filmeDevolvido;

    public ReservaFilme() {
    }

    public ReservaFilme(Filme filme, Cliente cliente, Double precoLocacao, Double taxaLocacao, Integer diasReserva,
                        Character reservado, LocalDateTime dataLocacao, LocalDateTime dataDevolucaoLocacao,
                        char statusLocacao, boolean filmeDevolvido) {
        this.cliente = cliente;
        this.filme = filme;
        this.precoLocacao = precoLocacao;
        this.taxaLocacao = taxaLocacao;
        this.diasReserva = diasReserva;
        this.reservado = reservado;
        this.dataLocacao = dataLocacao;
        this.dataDevolucaoLocacao = dataDevolucaoLocacao;
        this.statusLocacao = statusLocacao;
        this.filmeDevolvido = filmeDevolvido;
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

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean getFilmeDevolvido() {
        return filmeDevolvido;
    }

    public void setFilmeDevolvido(boolean filmeDevolvido) {
        this.filmeDevolvido = filmeDevolvido;
    }

}
