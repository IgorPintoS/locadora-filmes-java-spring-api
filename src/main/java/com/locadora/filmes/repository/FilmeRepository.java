package com.locadora.filmes.repository;

import com.locadora.filmes.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    boolean existsByTitulo(String titulo);
}
