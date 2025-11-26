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
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long idCliente){
        ClienteDTO clienteDTO = clienteServices.findById(idCliente);

        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<ClienteDTO> listaClientesDTO = clienteServices.findAll();

        return ResponseEntity.ok().body(listaClientesDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> adicionarCliente(@RequestBody ClienteDTO novoClienteDTO) {
        ClienteDTO clienteDTO = clienteServices.adicionarCliente(novoClienteDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarCliente(@PathVariable Long idCliente) {
        clienteServices.deletarCliente(idCliente);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long idCliente, @RequestBody ClienteDTO clienteAtualizadoDTO) {
        ClienteDTO clienteDTO = clienteServices.editarCliente(clienteAtualizadoDTO, idCliente);

        return ResponseEntity.ok().body(clienteDTO);
    }
}

