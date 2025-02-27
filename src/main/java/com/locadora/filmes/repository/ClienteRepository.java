package com.locadora.filmes.repository;

import com.locadora.filmes.entities.Cliente;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Id> {

    boolean validaCpfCnpjExistente(String cpfCnpj);
}
