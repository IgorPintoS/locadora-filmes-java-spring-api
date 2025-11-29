package com.locadora.filmes.controllers;

import com.locadora.filmes.controllers.dtos.FilmeDTO;
import com.locadora.filmes.services.FilmeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/locadora/filmes")
public class FilmeController {

    @Autowired
    private FilmeServices filmeServices;

    @GetMapping("/filmes")
    public ResponseEntity<List<FilmeDTO>> findAll() {
        List<FilmeDTO> filmeDTOList = filmeServices.findAll();

        return ResponseEntity.ok().body(filmeDTOList);
    }

    @GetMapping("/filmes/{idFilme}")
    public ResponseEntity<FilmeDTO> findById(@PathVariable Long idFilme) {
        FilmeDTO filmeDTO = filmeServices.findBydId(idFilme);

        return ResponseEntity.ok().body(filmeDTO);
    }

    @PostMapping("/filmes")
    public ResponseEntity<FilmeDTO> adicionarFilme(@RequestBody FilmeDTO novoFilmeDTO) {
        FilmeDTO filmeDTO = filmeServices.adicionarFilme(novoFilmeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(filmeDTO);
    }

    @PutMapping("/filmes/{idFilme}")
    public ResponseEntity<FilmeDTO> editarFilme(@PathVariable Long idFilme, @RequestBody FilmeDTO filmeAtualizadoDTO) {
        FilmeDTO filmeDTO = filmeServices.editarFilme(filmeAtualizadoDTO, idFilme);

        return ResponseEntity.ok().body(filmeDTO);
    }

    @DeleteMapping("/filmes/{idFilme}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long idFilme) {
        filmeServices.deletarFilme(idFilme);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/filmes/{idFilme}")
    public ResponseEntity<Void> adicionarEstoqueFilme(@PathVariable Long idFilme, @RequestBody Integer quantidade) {
        filmeServices.adicionarEstoqueFilme(idFilme, quantidade);

        return ResponseEntity.accepted().build();
    }
}
