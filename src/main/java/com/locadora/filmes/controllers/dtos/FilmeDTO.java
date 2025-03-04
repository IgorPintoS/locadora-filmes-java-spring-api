package com.locadora.filmes.controllers.dtos;

import jakarta.persistence.Id;

public record FilmeDTO(Id idFilme,
                       String titulo,
                       Double duracaoMin,
                       String genero,
                       String diretor,
                       Integer faixaEtaria,
                       String secao,
                       String prateleira,
                       Integer quantidadeEstoque) {
}
