package com.locadora.filmes.controllers.dtos;

import com.locadora.filmes.entities.Cliente;
import com.locadora.filmes.entities.Filme;
import java.time.LocalDateTime;

public record ReservaFilmeDTO(Long idReserva,
                              Integer diasReserva,
                              Double taxaLocacao,
                              Double precoLocacao,
                              Filme filme,
                              Cliente cliente,
                              Character reservado,
                              LocalDateTime dataLocacao,
                              LocalDateTime dataDevolucaoLocacao,
                              char statusLocacao) {
}
