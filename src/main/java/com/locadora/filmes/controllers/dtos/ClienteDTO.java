package com.locadora.filmes.controllers.dtos;

public record ClienteDTO(String nome,
                         String sobrenome,
                         Integer idade,
                         String endereco,
                         Integer numero,
                         String bairro,
                         String CpfCnpj,
                         Integer filmesLocadosMes) {
}
