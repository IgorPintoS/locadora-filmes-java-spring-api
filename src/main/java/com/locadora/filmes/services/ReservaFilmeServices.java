package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.ReservaFilmeDTO;
import com.locadora.filmes.entities.Cliente;
import com.locadora.filmes.entities.Filme;
import com.locadora.filmes.entities.ReservaFilme;
import com.locadora.filmes.exceptions.ClienteNotFoundException;
import com.locadora.filmes.exceptions.FilmeNotFoundException;
import com.locadora.filmes.exceptions.ReservaFilmeNotFoundException;
import com.locadora.filmes.repository.ClienteRepository;
import com.locadora.filmes.repository.FilmeRepository;
import com.locadora.filmes.repository.ReservaFilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaFilmeServices {

    @Autowired
    private ReservaFilmeRepository reservaFilmeRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ReservaFilme> findAll() {
        List<ReservaFilme> listaReservaFilmes = reservaFilmeRepository.findAll();

        if(listaReservaFilmes.isEmpty()){
            throw new ReservaFilmeNotFoundException("Não existe nenhuma reserva de filmes cadastrada.");
        }

        return listaReservaFilmes;
    }

    @Transactional(readOnly = true)
    public Optional<ReservaFilme> findById(Long idReserva) {
        Optional<ReservaFilme> resultadoReservaFilme = reservaFilmeRepository.findById(idReserva);

        if(resultadoReservaFilme.isEmpty()) {
            throw new ReservaFilmeNotFoundException("Reserva com o ID: " + idReserva + " não encontrada.");
        }

        return resultadoReservaFilme;
    }

    @Transactional
    public void adicionarReservaFilme(ReservaFilmeDTO reservaFilmeDTO) {

    Filme filme = filmeRepository.findById(reservaFilmeDTO.idFilme().getIdFilme())
            .orElseThrow(() -> new FilmeNotFoundException("Filme com ID " + reservaFilmeDTO.idFilme()
    + " não encontrado."));

    Cliente cliente = clienteRepository.findById(reservaFilmeDTO.idCliente().getIdCliente())
            .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + reservaFilmeDTO.idCliente()
    + " não encontrado."));

    ReservaFilme reservaFilme = new ReservaFilme();
    reservaFilme.setIdFilme(filme.getIdFilme());
    reservaFilme.setIdCliente(cliente.getIdCliente());
    reservaFilme.setDataLocacao(LocalDateTime.now());
    reservaFilme.setDiasReserva(reservaFilmeDTO.diasReserva());
    reservaFilme.setReservado(reservaFilmeDTO.reservado());
    reservaFilme.setPrecoLocacao(reservaFilmeDTO.precoLocacao());
    reservaFilme.setTaxaLocacao(reservaFilmeDTO.taxaLocacao());

    LocalDateTime dataDevolucao = LocalDateTime.now();
    dataDevolucao = dataDevolucao.plusDays(reservaFilmeDTO.diasReserva());

    reservaFilme.setDataDevolucaoLocacao(dataDevolucao);

    reservaFilme.setReservado('S');
    reservaFilme.setStatusLocacao('A');

    reservaFilmeRepository.save(reservaFilme);
    }

    public void finalizarReservaFilme(Long idReserva) {
        ReservaFilme reservaFilme = reservaFilmeRepository.findById(idReserva)
                .orElseThrow(() -> new FilmeNotFoundException("Reserva com o ID " + idReserva
                        + " não encontrado."));

        Double precoLocacao = reservaFilme.getPrecoLocacao();
        LocalDateTime dataDevolucaoCadastro = reservaFilme.getDataDevolucaoLocacao();
        LocalDateTime dataDevolucao = LocalDateTime.now();

        if(!dataDevolucao.equals(dataDevolucaoCadastro)){
            if(dataDevolucao.isBefore(dataDevolucaoCadastro)) {
                reservaFilme.setPrecoLocacao(precoLocacao);
            } else {
                long diferencaEmDias = ChronoUnit.DAYS.between(dataDevolucao, dataDevolucaoCadastro);

                switch ((int) diferencaEmDias) {
                    case 2 -> {
                        Double taxaLocacao = 2.00;
                        reservaFilme.setTaxaLocacao(taxaLocacao);
                        Double valorReajustado = reservaFilme.getTaxaLocacao() + reservaFilme.getPrecoLocacao();
                        reservaFilme.setPrecoLocacao(valorReajustado);
                    }
                    case 3 -> {
                        Double taxaLocacao = 3.50;
                        reservaFilme.setTaxaLocacao(taxaLocacao);
                        Double valorReajustado = reservaFilme.getTaxaLocacao() + reservaFilme.getPrecoLocacao();
                        reservaFilme.setPrecoLocacao(valorReajustado);
                    }
                    case 4 -> {
                        Double taxaLocacao = 4.50;
                        reservaFilme.setTaxaLocacao(taxaLocacao);
                        Double valorReajustado = reservaFilme.getTaxaLocacao() + reservaFilme.getPrecoLocacao();
                        reservaFilme.setPrecoLocacao(valorReajustado);
                    }
                    case 5 -> {
                        Double taxaLocacao = 6.50;
                        reservaFilme.setTaxaLocacao(taxaLocacao);
                        Double valorReajustado = reservaFilme.getTaxaLocacao() + reservaFilme.getPrecoLocacao();
                        reservaFilme.setPrecoLocacao(valorReajustado);
                    }
                    default -> {
                        if(diferencaEmDias > 5) {
                            Double taxaLocacao = 10.00;
                            reservaFilme.setTaxaLocacao(taxaLocacao);
                            Double valorReajustado = reservaFilme.getTaxaLocacao() + reservaFilme.getPrecoLocacao();
                            reservaFilme.setPrecoLocacao(valorReajustado);
                        }
                    }
                }
            }
        } else {
            reservaFilme.setPrecoLocacao(precoLocacao);
        }
        reservaFilme.setReservado('N');
        reservaFilme.setStatusLocacao('I');

        reservaFilmeRepository.save(reservaFilme);
    }
}
