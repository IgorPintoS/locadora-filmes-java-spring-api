package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.FilmeDTO;
import com.locadora.filmes.entities.Filme;
import com.locadora.filmes.exceptions.FilmeExistsException;
import com.locadora.filmes.exceptions.FilmeNotFoundException;
import com.locadora.filmes.exceptions.FilmeQuantityInvalidException;
import com.locadora.filmes.repository.FilmeRepository;
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
    public Optional<Filme> findBydId(FilmeDTO filmeDTO){
        Optional<Filme> resultadoFilme = filmeRepository.findById(filmeDTO.idFilme());

        if(resultadoFilme.isEmpty()) {
            throw new FilmeNotFoundException("Filme com o ID: " + filmeDTO.idFilme() + " não encontrado.");
        }

        return resultadoFilme;
    }

    @Transactional
    public void adicionarFilme(FilmeDTO filmeDTO){

        String filmeExistente = filmeDTO.titulo();

        if(filmeRepository.validaFilmeExistente(filmeExistente)) {
            throw new FilmeExistsException("Já existe um filme cadastrado com o título " + filmeExistente + ".");
        }

        Filme filme = new Filme();
        filme.setTitulo(filmeExistente);
        filme.setDiretor(filmeDTO.diretor());
        filme.setGenero(filmeDTO.genero());
        filme.setDuracaoMin(filmeDTO.duracaoMin());
        filme.setFaixaEtaria(filmeDTO.faixaEtaria());

        filmeRepository.save(filme);
    }

    @Transactional
    public void deletarFilme(FilmeDTO filmeDTO){
        if(!filmeRepository.existsById(filmeDTO.idFilme())) {
            throw new FilmeExistsException("Filme com o ID: " + filmeDTO.idFilme() + " não encontrado.");
        }

        filmeRepository.deleteById(filmeDTO.idFilme());
    }

    @Transactional
    public void editarFilme(FilmeDTO filmeDTO){
        Filme filme = filmeRepository.findById(filmeDTO.idFilme())
                .orElseThrow(() -> new FilmeNotFoundException("Filme com o ID: " + filmeDTO.idFilme() + " não encontrado."));

        String filmeExistente = filmeDTO.titulo();

        if(filmeRepository.validaFilmeExistente(filmeExistente)) {
            throw new FilmeExistsException("Já existe um filme cadastrado com o título " + filmeExistente + ".");
        }

        filme.setTitulo(filmeExistente);
        filme.setDiretor(filmeDTO.diretor());
        filme.setGenero(filmeDTO.genero());
        filme.setDuracaoMin(filmeDTO.duracaoMin());
        filme.setFaixaEtaria(filmeDTO.faixaEtaria());

        filmeRepository.save(filme);

    }

    @Transactional
    public void adicionarEstoqueFilme(FilmeDTO filmeDTO){
        Filme filme = filmeRepository.findById(filmeDTO.idFilme())
                .orElseThrow(() -> new FilmeNotFoundException("Filme com o ID: " + filmeDTO.idFilme() + " não encontrado."));

        if(filmeDTO.quantidadeEstoque() < 0) {
            throw new FilmeQuantityInvalidException("Quantidade informada é inválida, precisa ser maior que zero.");
        }

        filme.setQuantidadeEstoque(filmeDTO.quantidadeEstoque());

        filmeRepository.save(filme);

    }
}
