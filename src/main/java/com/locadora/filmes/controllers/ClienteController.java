package com.locadora.filmes.controllers;

import com.locadora.filmes.controllers.dtos.ClienteDTO;
import com.locadora.filmes.entities.Cliente;
import com.locadora.filmes.exceptions.CpfCnpjExistsException;
import com.locadora.filmes.exceptions.CpfCnpjInvalidException;
import com.locadora.filmes.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/locadora/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping(value = "/{idCliente}")
    public Cliente findById(@PathVariable Long idCliente){
        Cliente cliente = clienteServices.findById(idCliente);
        return cliente;
    }

    @GetMapping
    public List<Cliente> findAll(){
        List<Cliente> listaClientes = clienteServices.findAll();
        return listaClientes;
    }

    @PostMapping
    public ResponseEntity<?> adicionarCliente(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteServices.adicionarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CpfCnpjInvalidException | CpfCnpjExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

