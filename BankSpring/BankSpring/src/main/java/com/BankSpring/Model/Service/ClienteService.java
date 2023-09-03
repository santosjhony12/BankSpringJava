package com.BankSpring.Model.Service;

import com.BankSpring.Model.Exception.ClienteNotFoundException;
import com.BankSpring.Model.DTO.Cliente;
import com.BankSpring.Model.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    public Cliente buscarClientePorCpf(String cpf){
        return clienteRepository.findByCpf(cpf);
    }
    public Cliente atualizarSenha(Long id, String senha){
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if(optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();
            cliente.setSenha(senha);
            return clienteRepository.save(cliente);
        }else{
            throw new ClienteNotFoundException("Cliente não encontrado");
        }
    }
}
