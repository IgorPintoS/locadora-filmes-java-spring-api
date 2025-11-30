package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.ClienteDTO;
import com.locadora.filmes.controllers.dtos.FilmeDTO;
import com.locadora.filmes.controllers.dtos.ReservaFilmeDTO;
import com.locadora.filmes.entities.ReservaFilme;
import com.locadora.filmes.enums.StatusLocacao;
import com.locadora.filmes.enums.StatusReserva;
import com.locadora.filmes.exceptions.FilmeNotFoundException;
import com.locadora.filmes.exceptions.ReservaFilmeNotFoundException;
import com.locadora.filmes.repository.ReservaFilmeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReservaFilmeServices {

    @Autowired
    private ReservaFilmeRepository reservaFilmeRepository;

    @Autowired
    private FilmeServices filmeServices;

    @Autowired
    private ClienteServices clienteServices;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    public List<ReservaFilmeDTO> findAll() {
        List<ReservaFilme> listaReservaFilmes = reservaFilmeRepository.findAll();

        List<ReservaFilmeDTO> reservaFilmeDTOS = listaReservaFilmes
                .stream()
                .map(reservaFilme -> modelMapper.map(reservaFilme, ReservaFilmeDTO.class))
                .toList();

        if(listaReservaFilmes.isEmpty()){
            throw new ReservaFilmeNotFoundException("Não existe nenhuma reserva de filmes cadastrada.");
        }

        return reservaFilmeDTOS;
    }

    @Transactional(readOnly = true)
    public ReservaFilmeDTO findById(Long idReserva) {
        ReservaFilme resultadoReservaFilme = reservaFilmeRepository.findById(idReserva)
                .orElseThrow(() -> new ReservaFilmeNotFoundException("Reserva com o ID: " + idReserva + " não encontrada."));

        ReservaFilmeDTO reservaFilmeDTO = modelMapper.map(resultadoReservaFilme, ReservaFilmeDTO.class);

        return reservaFilmeDTO;
    }

    @Transactional
    public ReservaFilmeDTO adicionarReservaFilme(ReservaFilmeDTO novaReservaFilmeDTO) {

    FilmeDTO filmeDTO = filmeServices.findBydId(novaReservaFilmeDTO.filme().getIdFilme());

    ClienteDTO clienteDTO = clienteServices.findById(novaReservaFilmeDTO.cliente().getIdCliente());

    ReservaFilme reservaFilme = new ReservaFilme();
    reservaFilme.setFilme(reservaFilme.getFilme());
    reservaFilme.setCliente(reservaFilme.getCliente());
    reservaFilme.setDataLocacao(LocalDateTime.now());
    reservaFilme.setDiasReserva(novaReservaFilmeDTO.diasReserva());
    reservaFilme.setPrecoLocacao(novaReservaFilmeDTO.precoLocacao());
    reservaFilme.setTaxaLocacao(novaReservaFilmeDTO.taxaLocacao());

    LocalDateTime dataDevolucao = LocalDateTime.now();
    dataDevolucao = dataDevolucao.plusDays(novaReservaFilmeDTO.diasReserva());

    reservaFilme.setDataDevolucaoLocacao(dataDevolucao);

    reservaFilme.setStatusReserva(StatusReserva.RESERVADO);
    reservaFilme.setStatusLocacao(StatusLocacao.ATIVO);

    int filmesPorCliente = reservasPorCliente(reservaFilme.getCliente().getIdCliente());

    filmeServices.informarFilmeLocado(filmeDTO.idFilme(), true);
    clienteServices.adicionarContagemFilmesLocados(clienteDTO.idCliente(), filmesPorCliente);

    reservaFilmeRepository.save(reservaFilme);

    return modelMapper.map(reservaFilme, ReservaFilmeDTO.class);
    }

    @Transactional
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

        reservaFilme.setStatusReserva(StatusReserva.NAORESERVADO);
        reservaFilme.setStatusLocacao(StatusLocacao.INATIVO);

        reservaFilmeRepository.save(reservaFilme);
    }

    private int reservasPorCliente(Long idCliente){
        List<ReservaFilme> listaReservaFilmes = reservaFilmeRepository.findAll();
        
        AtomicInteger contadorFilmes = new AtomicInteger();

        listaReservaFilmes.stream().filter(reservaFilme -> reservaFilme.getCliente().getIdCliente().equals(idCliente))
                .forEach(reservaFilme -> contadorFilmes.getAndIncrement());

        return contadorFilmes.get();
    }
}
