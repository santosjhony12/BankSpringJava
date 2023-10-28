package com.BankSpring.Model.Service;

import com.BankSpring.Model.Exception.ClienteNotFoundException;
import com.BankSpring.Model.DTO.Cliente;
import com.BankSpring.Model.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
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

    public boolean validarCPF(String cpf){
        if (cpf.length() != 11) {
            return false;
        }
        Integer[] cpfArrayInteger = new Integer[11];
        Integer soma = 0;
        Integer resultado = 0;

        for (int i = 0; i < 11; i++) {
            cpfArrayInteger[i] = Integer.valueOf(cpf.substring(i, i + 1));
        }
        for (int i = 0; i < 9; i++) {
            soma += cpfArrayInteger[i] * (10 - i);
        }
        resultado = soma % 11;
        resultado = (resultado < 2) ? 0 : 11 - resultado;

        if (resultado != cpfArrayInteger[9]) {
            return false;
        }
        soma = 0;

        for (int i = 0; i < 10; i++) {
            soma += cpfArrayInteger[i] * (11 - i);
        }

        resultado = soma % 11;
        resultado = (resultado < 2) ? 0 : 11 - resultado;

        return resultado == cpfArrayInteger[10];
    }
}
