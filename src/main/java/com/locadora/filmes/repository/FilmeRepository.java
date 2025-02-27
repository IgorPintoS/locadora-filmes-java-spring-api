package com.locadora.filmes.repository;

import com.locadora.filmes.entities.Filme;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Id> {

    boolean validaFilmeExistente(String titulo);
}
