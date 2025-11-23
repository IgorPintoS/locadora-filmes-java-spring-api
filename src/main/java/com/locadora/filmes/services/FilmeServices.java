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
    public Filme findBydId(Long idFilme){
        Filme filme = filmeRepository.findById(idFilme)
                .orElseThrow(() -> new FilmeNotFoundException("Filme com o ID: " + idFilme + " não encontrado."));

        return filme;
    }

    @Transactional
    public void adicionarFilme(Filme filmeNovo){
        String filmeExistente = filmeNovo.getTitulo();

        this.verificarTituloFilmeExistente(filmeExistente);

        Filme filme = new Filme();
        filme.setTitulo(filmeExistente);
        filme.setDiretor(filmeNovo.getDiretor());
        filme.setGenero(filmeNovo.getGenero());
        filme.setDuracaoMin(filmeNovo.getDuracaoMin());
        filme.setFaixaEtaria(filmeNovo.getFaixaEtaria());

        filmeRepository.save(filme);
    }

    @Transactional
    public void deletarFilme(Long idFilme){
        this.findBydId(idFilme);

        filmeRepository.deleteById(idFilme);
    }

    @Transactional
    public void editarFilme(Filme filmeAtualizado, Long idFilme){
        Filme filme = this.findBydId(idFilme);

        String filmeExistente = filmeAtualizado.getTitulo();

        this.verificarTituloFilmeExistente(filmeExistente);

        filme.setTitulo(filmeExistente);
        filme.setDiretor(filmeAtualizado.getDiretor());
        filme.setGenero(filmeAtualizado.getGenero());
        filme.setDuracaoMin(filmeAtualizado.getDuracaoMin());
        filme.setFaixaEtaria(filmeAtualizado.getFaixaEtaria());

        filmeRepository.save(filme);
    }

    @Transactional
    public void adicionarEstoqueFilme(Long idFilme, Integer quantidade){
        Filme filme = this.findBydId(idFilme);

        if(quantidade < 0) {
            throw new FilmeQuantityInvalidException("Quantidade informada é inválida, precisa ser maior que zero.");
        }

        filme.setQuantidadeEstoque(quantidade);

        filmeRepository.save(filme);

    }

    private void verificarTituloFilmeExistente(String titulo) {
        filmeRepository.existsByTitulo(titulo);

        if (titulo.isEmpty()) {
            throw new FilmeExistsException("Já existe um filme cadastrado com o título " + titulo + ".");
        }
    }
}
