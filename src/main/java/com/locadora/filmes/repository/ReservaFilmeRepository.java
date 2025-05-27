package com.locadora.filmes.repository;

import com.locadora.filmes.entities.ReservaFilme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaFilmeRepository extends JpaRepository<ReservaFilme, Long> {

}
