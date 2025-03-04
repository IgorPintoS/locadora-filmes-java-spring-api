package com.locadora.filmes.controllers.dtos;

import com.locadora.filmes.entities.Cliente;
import com.locadora.filmes.entities.Filme;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

public record ReservaFilmeDTO(Id reservaId,
                              Integer diasReserva,
                              Double taxaLocacao,
                              Double precoLocacao,
                              Filme idFilme,
                              Cliente idCliente,
                              Character reservado,
                              LocalDateTime dataLocacao) {
}
