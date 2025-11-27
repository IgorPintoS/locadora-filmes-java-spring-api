package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.FilmeDTO;
import com.locadora.filmes.entities.Filme;
import com.locadora.filmes.exceptions.FilmeExistsException;
import com.locadora.filmes.exceptions.FilmeNotFoundException;
import com.locadora.filmes.exceptions.FilmeQuantityInvalidException;
import com.locadora.filmes.repository.FilmeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmeServices {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private ReservaFilmeServices reservaFilmeServices;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    public List<FilmeDTO> findAll(){
        List<Filme> listaFilmes = filmeRepository.findAll();

        List<FilmeDTO> filmeDTOList = listaFilmes
                .stream()
                .map(filme -> modelMapper.map(filme, FilmeDTO.class))
                .toList();

        return filmeDTOList;
    }

    @Transactional(readOnly = true)
    public FilmeDTO findBydId(Long idFilme){
        Filme filme = filmeRepository.findById(idFilme)
                .orElseThrow(() -> new FilmeNotFoundException("Filme com o ID: " + idFilme + " não encontrado."));

        FilmeDTO filmeDTO = modelMapper.map(filme, FilmeDTO.class);

        return filmeDTO;
    }

    @Transactional
    public FilmeDTO adicionarFilme(FilmeDTO filmeNovoDTO){
        String filmeExistente = filmeNovoDTO.titulo();

        this.verificarTituloFilmeExistente(filmeExistente);

        Filme filme = new Filme();
        filme.setTitulo(filmeNovoDTO.titulo());
        filme.setDiretor(filmeNovoDTO.diretor());
        filme.setGenero(filmeNovoDTO.genero());
        filme.setFaixaEtaria(filmeNovoDTO.faixaEtaria());
        filme.setDuracaoMin(filmeNovoDTO.duracaoMin());
        filme.setSecao(filmeNovoDTO.secao());

        filmeRepository.save(filme);

        return modelMapper.map(filme, FilmeDTO.class);
    }

    @Transactional
    public void deletarFilme(Long idFilme){
        this.findBydId(idFilme);

        filmeRepository.deleteById(idFilme);
    }

    @Transactional
    public FilmeDTO editarFilme(FilmeDTO filmeAtualizadoDTO, Long idFilme){
        FilmeDTO filmeDTO = this.findBydId(idFilme);

        Filme filme = modelMapper.map(filmeDTO, Filme.class);

        String filmeExistente = filme.getTitulo();

        this.verificarTituloFilmeExistente(filmeExistente);

        filme.setTitulo(filmeExistente);
        filme.setDiretor(filmeAtualizadoDTO.diretor());
        filme.setGenero(filmeAtualizadoDTO.genero());
        filme.setDuracaoMin(filmeAtualizadoDTO.duracaoMin());
        filme.setFaixaEtaria(filmeAtualizadoDTO.faixaEtaria());

        filmeRepository.save(filme);

        return modelMapper.map(filme, FilmeDTO.class);
    }

    @Transactional
    public void adicionarEstoqueFilme(Long idFilme, Integer quantidade){
        FilmeDTO filmeDTO = this.findBydId(idFilme);

        Filme filme =  modelMapper.map(filmeDTO, Filme.class);

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
