package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.FilmeDTO;
import com.locadora.filmes.entities.Filme;
import com.locadora.filmes.exceptions.FilmeExistsException;
import com.locadora.filmes.exceptions.FilmeNotFoundException;
import com.locadora.filmes.repository.FilmeRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeServices {

    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional(readOnly = true)
    public List<Filme> findAll(){
        List<Filme> listaFilmes = filmeRepository.findAll();

        if(listaFilmes.isEmpty()){
            throw new FilmeNotFoundException("Não existem filmes cadastrados.");
        }

        return listaFilmes;
    }

    @Transactional(readOnly = true)
    public Optional<Filme> findBydId(Id idFilme){
        Optional<Filme> resultadoFilme = filmeRepository.findById(idFilme);

        if(resultadoFilme.isEmpty()) {
            throw new FilmeNotFoundException("Filme com o ID: " + idFilme + " não encontrado.");
        }

        return resultadoFilme;
    }

    @Transactional
    public void adicionarFilme(FilmeDTO filmeDTO){
        if(filmeRepository.validaFilmeExistente(filmeDTO.titulo())) {
            throw new FilmeExistsException("Já existe um filme cadastrado com o título " + filmeDTO.titulo() + ".");
        }

        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.titulo());
        filme.setDiretor(filmeDTO.diretor());
        filme.setGenero(filmeDTO.genero());
        filme.setDuracaoMin(filmeDTO.duracaoMin());
        filme.setFaixaEtaria(filmeDTO.faixaEtaria());

        filmeRepository.save(filme);
    }
}
