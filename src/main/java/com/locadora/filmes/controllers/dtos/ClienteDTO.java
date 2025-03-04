package com.locadora.filmes.controllers.dtos;

import jakarta.persistence.Id;

public record ClienteDTO(Id idCliente,
                         String nome,
                         String sobrenome,
                         Integer idade,
                         String endereco,
                         Integer numero,
                         String bairro,
                         String CpfCnpj,
                         Integer filmesLocadosMes) {
}
