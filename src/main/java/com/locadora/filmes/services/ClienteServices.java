package com.locadora.filmes.services;

import com.locadora.filmes.controllers.dtos.ClienteDTO;
import com.locadora.filmes.entities.Cliente;
import com.locadora.filmes.exceptions.ClienteNotFoundException;
import com.locadora.filmes.exceptions.CpfCnpjExistsException;
import com.locadora.filmes.exceptions.CpfCnpjInvalidException;
import com.locadora.filmes.repository.ClienteRepository;
import com.locadora.filmes.services.utility.CpfCnpjFormatar;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        List<Cliente> listaClientes = clienteRepository.findAll();

        List<ClienteDTO> clienteDTOList = listaClientes
                .stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .toList();

        return clienteDTOList;
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com o ID: " + idCliente + " não encontrado."));

        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);

        return clienteDTO;
    }

    @Transactional
    public ClienteDTO adicionarCliente(ClienteDTO novoClienteDTO) {
        String cpfCnpj = novoClienteDTO.cpfCnpj();

        if(!CpfCnpjFormatar.validarCpfCnpj(cpfCnpj)){
            throw new CpfCnpjInvalidException("CPF/CNPJ com dígitos inválidos.");
        }

        CpfCnpjFormatar.formatarCpfCnpj(cpfCnpj);

        Optional<Cliente> clienteCpfCnpj = clienteRepository.existsByCpfCnpj(cpfCnpj);

        if(clienteCpfCnpj.isPresent()) {
            throw new CpfCnpjExistsException("Já existe um cadastro de cliente com o CPF/CNPJ: " + cpfCnpj + ".");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(novoClienteDTO.nome());
        cliente.setSobrenome(novoClienteDTO.sobrenome());
        cliente.setIdade(novoClienteDTO.idade());
        cliente.setEndereco(novoClienteDTO.endereco());
        cliente.setBairro(novoClienteDTO.bairro());
        cliente.setNumero(novoClienteDTO.numero());
        cliente.setCpfCnpj(cpfCnpj);

        clienteRepository.save(cliente);

        ClienteDTO clienteDTO = modelMapper.map(cliente, ClienteDTO.class);

        return clienteDTO;
    }

    @Transactional
    public void deletarCliente(Long idCliente){
        this.findById(idCliente);

        clienteRepository.deleteById(idCliente);
    }

    @Transactional
    public ClienteDTO editarCliente(ClienteDTO clienteAtualizadoDTO, Long idCliente){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        String cpfCnpj = clienteAtualizadoDTO.cpfCnpj();

        if(!CpfCnpjFormatar.validarCpfCnpj(cpfCnpj)){
            throw new CpfCnpjInvalidException("CPF/CNPJ com dígitos inválidos.");
        }

        CpfCnpjFormatar.formatarCpfCnpj(cpfCnpj);

        Optional<Cliente> clienteCpfCnpj = clienteRepository.existsByCpfCnpj(cpfCnpj);

        if(clienteCpfCnpj.isPresent() && !clienteCpfCnpj.get().getIdCliente().equals(cliente.get().getIdCliente())) {
            throw new CpfCnpjExistsException("Já existe um cadastro de cliente com o CPF/CNPJ: " + cpfCnpj + ".");
        }

        Cliente clienteSalvo = cliente.get();
        modelMapper.map(clienteAtualizadoDTO, clienteSalvo);
        clienteSalvo.setCpfCnpj(cpfCnpj);

        clienteRepository.save(clienteSalvo);

        return modelMapper.map(clienteSalvo, ClienteDTO.class);
    }

    @Transactional
    public void adicionarContagemFilmesLocados(Long idCliente, int quantidade) {
        ClienteDTO clienteDTO = this.findById(idCliente);
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);

        if(quantidade > 0){
            cliente.setFilmesLocados(cliente.getFilmesLocados() + 1);
        } else {
            cliente.setFilmesLocados(1);
        }

        clienteRepository.save(cliente);
    }
}