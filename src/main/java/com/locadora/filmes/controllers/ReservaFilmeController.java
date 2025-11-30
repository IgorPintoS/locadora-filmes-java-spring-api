package com.locadora.filmes.controllers;

import com.locadora.filmes.controllers.dtos.ReservaFilmeDTO;
import com.locadora.filmes.services.ReservaFilmeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/locadora/reservafilmes")
public class ReservaFilmeController {

    @Autowired
    private ReservaFilmeServices reservaFilmeServices;

    @GetMapping("/reservafilmes")
    public ResponseEntity<List<ReservaFilmeDTO>> findAll() {
        List<ReservaFilmeDTO> reservaFilmeDTOList = reservaFilmeServices.findAll();

        return ResponseEntity.ok().body(reservaFilmeDTOList);
    }

    @GetMapping("/reservafilmes/{id}")
    public ResponseEntity<ReservaFilmeDTO> findById(@PathVariable Long idReserva) {
        ReservaFilmeDTO reservaFilmeDTO = reservaFilmeServices.findById(idReserva);

        return ResponseEntity.ok().body(reservaFilmeDTO);
    }

    @PostMapping("/reservafilme")
    public ResponseEntity<ReservaFilmeDTO> adicionarReservaFilme(@RequestBody ReservaFilmeDTO novaReservaFilmeDTO) {
        ReservaFilmeDTO reservaFilmeDTO = reservaFilmeServices.adicionarReservaFilme(novaReservaFilmeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaFilmeDTO);
    }

    @PatchMapping("/reservafilme/{id}")
    public ResponseEntity<Void> finalizarReservaFilme(@PathVariable Long idReserva) {
        reservaFilmeServices.finalizarReservaFilme(idReserva);

        return ResponseEntity.ok().build();
    }
}
