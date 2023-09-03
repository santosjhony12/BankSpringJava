package com.BankSpring.Model.Service;

import com.BankSpring.Model.Exception.ClienteNotFoundException;
import com.BankSpring.Model.DTO.Cliente;
import com.BankSpring.Model.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    public Cliente inserirCliente(Cliente cliente){
        clienteRepository.save(cliente);
        return cliente;
    }
    public Cliente buscarClientePorCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
    public Cliente atualizarSenha(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    public void excluirClientePorCpf(String cpf) {
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        } else {
            throw new ClienteNotFoundException("Cliente não encontrado!");
        }
    }
}
