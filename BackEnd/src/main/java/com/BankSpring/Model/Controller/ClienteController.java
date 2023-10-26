package com.BankSpring.Model.Controller;

import com.BankSpring.Model.Exception.ClienteNotFoundException;
import com.BankSpring.Model.Service.ClienteService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.BankSpring.Model.DTO.Cliente;
import com.BankSpring.Model.Repository.ClienteRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClienteController {

    ClienteRepository clienteRepository;
    ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository, ClienteService clienteService) {
        this.clienteRepository = clienteRepository;
        this.clienteService = clienteService;
    }
    @GetMapping("/clientes")
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping("/cadastroCliente")
    public Cliente saveCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/cliente/{cpf}")
    public Cliente buscarClientePorCpf(@PathVariable String cpf){
        return clienteService.buscarClientePorCpf(cpf);
    }

    @DeleteMapping("/deleteCliente/{id}")
    public void excluirClientePorId(@PathVariable Long id){
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if(optionalCliente.isPresent()){
            clienteRepository.deleteById(id);
        }else{
            throw new ClienteNotFoundException("Cliente não encontrado");
        }
    }
    @PutMapping("/alterarSenha")
    public Cliente atualizarSenha(@RequestBody Map<String, Object> requestBody){
        Long id = ((Number) requestBody.get("id")).longValue();
        String senha = (String) requestBody.get("senha");
        return clienteService.atualizarSenha(id, senha);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, Object> requestBody){
        String cpf = (String) requestBody.get("cpf");
        String senha = (String) requestBody.get("senha");

        Cliente cliente = clienteRepository.findByCpf(cpf);
        if(cliente != null){
            if(cliente.getSenha().equals(senha)){
                return ResponseEntity.ok("Login bem sucedido");
            }else{
                return ResponseEntity.ok("Senha Incorreta");
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
