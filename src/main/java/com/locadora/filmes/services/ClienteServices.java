package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.ClienteDTO;
import com.locadora.filmes.entities.Cliente;
import com.locadora.filmes.exceptions.ClienteNotFoundException;
import com.locadora.filmes.exceptions.CpfCnpjExistsException;
import com.locadora.filmes.exceptions.CpfCnpjInvalidException;
import com.locadora.filmes.repository.ClienteRepository;
import com.locadora.filmes.services.utility.CpfCnpjFormatar;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        List<Cliente> listaClientes = clienteRepository.findAll();

        if (listaClientes.isEmpty()) {
            throw new ClienteNotFoundException("Não existem clientes cadastrados.");
        }
        return listaClientes;
    }

    @Transactional(readOnly = true)
    public Cliente findById(Id idCliente) {
        Optional<Cliente> resultadoCliente = clienteRepository.findById(idCliente);

        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException("Não existem cliente com o Id " + idCliente));

    }

    public void adicionarCliente(ClienteDTO clienteDTO) {

        String cpfCnpj = clienteDTO.CpfCnpj();

        if(!CpfCnpjFormatar.validarCpfCnpj(cpfCnpj)){
            throw new CpfCnpjInvalidException("CPF/CNPJ com dígitos inválidos.");
        }

        CpfCnpjFormatar.formatarCpfCnpj(cpfCnpj);

        if(clienteRepository.validaCpfCnpjExistente(cpfCnpj)) {
            throw new CpfCnpjExistsException("Já existe um cadastro de cliente com o CPF/CNPJ: " + cpfCnpj + ".");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.nome());
        cliente.setSobrenome(clienteDTO.sobrenome());
        cliente.setIdade(clienteDTO.idade());
        cliente.setEndereco(clienteDTO.endereco());
        cliente.setBairro(clienteDTO.bairro());
        cliente.setNumero(clienteDTO.numero());
        cliente.setCpfCnpj(cpfCnpj);

        clienteRepository.save(cliente);
    }
}