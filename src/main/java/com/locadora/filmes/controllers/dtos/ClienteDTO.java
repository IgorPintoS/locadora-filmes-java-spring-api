package com.locadora.filmes.controllers.dtos;

public record ClienteDTO(Long idCliente,
                         String nome,
                         String sobrenome,
                         Integer idade,
                         String endereco,
                         Integer numero,
                         String bairro,
                         String cpfCnpj,
                         Integer filmesLocados) {

}
