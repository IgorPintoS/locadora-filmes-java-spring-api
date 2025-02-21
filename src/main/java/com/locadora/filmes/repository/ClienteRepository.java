package com.locadora.filmes.repository;

import com.locadora.filmes.entities.Clientes;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Id> {
}
