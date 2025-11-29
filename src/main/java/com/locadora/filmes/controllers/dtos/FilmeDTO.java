package com.locadora.filmes.controllers.dtos;

public record FilmeDTO(Long idFilme,
                       String titulo,
                       Double duracaoMin,
                       String genero,
                       String diretor,
                       Integer faixaEtaria,
                       String secao,
                       String prateleira,
                       Integer quantidadeEstoque,
                       boolean locado) {
}
