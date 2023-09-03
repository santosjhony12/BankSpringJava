package com.BankSpring.Model.Controller;

import com.BankSpring.Model.Service.ClienteService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.BankSpring.Model.DTO.Cliente;
import com.BankSpring.Model.Repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@RestController
public class ClienteController {

    ClienteRepository clienteRepository;
    ClienteService clienteService;
    @GetMapping("/carro")
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping("/cliente")
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/cliente/{id}")
    public Optional<Cliente> getClienteByCpf(@PathVariable Long id){
        return clienteRepository.findById(id);
    }

    @DeleteMapping("/cliente/{cpf}")
    public void deleteCliente(@PathVariable String cpf){
        clienteService.excluirClientePorCpf(cpf);
    }
}
