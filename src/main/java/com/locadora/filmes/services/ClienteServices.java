package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.ClienteDTO;
import com.locadora.filmes.entities.Cliente;
import com.locadora.filmes.exceptions.ClienteNotFoundException;
import com.locadora.filmes.exceptions.CpfCnpjExistsException;
import com.locadora.filmes.exceptions.CpfCnpjInvalidException;
import com.locadora.filmes.repository.ClienteRepository;
import com.locadora.filmes.services.utility.CpfCnpjFormatar;
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
    public Optional<Cliente> findById(ClienteDTO clienteDTO) {
        Optional<Cliente> resultadoCliente = clienteRepository.findById(clienteDTO.idCliente());

        if(resultadoCliente.isEmpty()) {
            throw new ClienteNotFoundException("Cliente com o ID: " + clienteDTO.idCliente() + " não encontrado.");
        }

        return resultadoCliente;
    }

    @Transactional
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

    @Transactional
    public void deletarCliente(ClienteDTO clienteDTO){
        if(!clienteRepository.existsById(clienteDTO.idCliente())){
            throw new ClienteNotFoundException("Cliente com o ID: " + clienteDTO.idCliente() + " não encontrado.");
        }

        clienteRepository.deleteById(clienteDTO.idCliente());

    }

    @Transactional
    public void editarCliente(ClienteDTO clienteDTO){
        Cliente cliente = clienteRepository.findById(clienteDTO.idCliente()).
                orElseThrow(() -> new ClienteNotFoundException("Cliente com o ID: " + clienteDTO.idCliente() + " não encontrado."));

        String cpfCnpj = clienteDTO.CpfCnpj();

        if(!CpfCnpjFormatar.validarCpfCnpj(cpfCnpj)){
            throw new CpfCnpjInvalidException("CPF/CNPJ com dígitos inválidos.");
        }

        CpfCnpjFormatar.formatarCpfCnpj(cpfCnpj);

        if(clienteRepository.validaCpfCnpjExistente(cpfCnpj)) {
            throw new CpfCnpjExistsException("Já existe um cadastro de cliente com o CPF/CNPJ: " + cpfCnpj + ".");
        }

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