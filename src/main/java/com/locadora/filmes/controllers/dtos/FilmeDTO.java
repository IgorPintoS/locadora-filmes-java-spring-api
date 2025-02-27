package com.locadora.filmes.controllers.dtos;

public record FilmeDTO(String titulo,
                       Double duracaoMin,
                       String genero,
                       String diretor,
                       Integer faixaEtaria,
                       String secao,
                       String prateleira,
                       Integer quantidadeEstoque) {
}
