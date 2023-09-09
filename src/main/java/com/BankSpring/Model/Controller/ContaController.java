package com.BankSpring.Model.Controller;

import com.BankSpring.Model.DTO.ContaBancaria;
import com.BankSpring.Model.Repository.ContaRepository;
import com.BankSpring.Model.Service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ContaController {
    private final ContaRepository contaRepository;
    private final ContaService contaService;

    @Autowired
    public ContaController(ContaRepository contaRepository, ContaService contaService){
        this.contaService = contaService;
        this.contaRepository = contaRepository;
    }

    @GetMapping("/contas")
    public List<ContaBancaria> buscarPoupanca(){
        return contaRepository.findAll();
    }

    @GetMapping("/conta/{id}")
    public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable Long id){
        Optional<ContaBancaria> optionalContaPoupanca = contaRepository.findById(id);

        if(optionalContaPoupanca.isPresent()){
            return ResponseEntity.ok(optionalContaPoupanca.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/novaConta")
    public ContaBancaria novaPoupanca(@RequestBody ContaBancaria contaPoupanca){
        return contaRepository.save(contaPoupanca);
    }

    @PutMapping("/transferir")
    public ResponseEntity<ContaBancaria> transferir(@RequestBody Map<String, Object> requestBody){
        Long id = ((Number) requestBody.get("id")).longValue();
        double valor = ((Number) requestBody.get("valor")).doubleValue();
        ContaBancaria result = contaService.transferir(id, valor);
        if (result!=null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/sacar")
    public ResponseEntity<ContaBancaria> sacar(@RequestBody Map<String, Object> requestBody){
        Long id = ((Number) requestBody.get("id")).longValue();
        double valor = ((Number) requestBody.get("valor")).doubleValue();
        ContaBancaria result = contaService.sacar(id, valor);
        if(result!=null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/depositar")
    public ResponseEntity<ContaBancaria> depositar(@RequestBody Map<String, Object> requestBody){
        Long id = ((Number) requestBody.get("id")).longValue();
        double valor = ((Number) requestBody.get("valor")).doubleValue();
        ContaBancaria result = contaService.depositar(id, valor);

        if(result!= null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
