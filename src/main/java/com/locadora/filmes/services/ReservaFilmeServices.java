package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.ReservaFilmeDTO;
import com.locadora.filmes.entities.ReservaFilme;
import com.locadora.filmes.exceptions.ReservaFilmeNotFoundException;
import com.locadora.filmes.repository.ClienteRepository;
import com.locadora.filmes.repository.FilmeRepository;
import com.locadora.filmes.repository.ReservaFilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional<ReservaFilme> findById(ReservaFilmeDTO reservaFilmeDTO) {
        Optional<ReservaFilme> resultadoReservaFilme = reservaFilmeRepository.findById(reservaFilmeDTO.idReserva());

        if(resultadoReservaFilme.isEmpty()) {
            throw new ReservaFilmeNotFoundException("Reserva com o ID: " + reservaFilmeDTO.idReserva() + " não encontrada.");
        }

        return resultadoReservaFilme;
    }

    @Transactional
    public void adicionarReservaFilme(ReservaFilmeDTO reservaFilmeDTO) {


    }
}
