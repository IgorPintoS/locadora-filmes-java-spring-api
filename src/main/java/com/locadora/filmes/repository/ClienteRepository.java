package com.locadora.filmes.repository;

import com.locadora.filmes.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpfCnpj(String cpfCnpj);
}
