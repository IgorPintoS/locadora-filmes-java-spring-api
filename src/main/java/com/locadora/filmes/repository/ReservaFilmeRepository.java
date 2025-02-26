package com.locadora.filmes.repository;

import com.locadora.filmes.entities.ReservaFilme;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaFilmeRepository extends JpaRepository<ReservaFilme, Id> {
}
