package com.locadora.filmes.repository;

import com.locadora.filmes.entities.Filmes;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filmes, Id> {
}
