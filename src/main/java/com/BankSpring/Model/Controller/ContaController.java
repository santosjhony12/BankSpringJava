package com.BankSpring.Model.Controller;

import com.BankSpring.Model.DTO.ContaBancaria;
import com.BankSpring.Model.Repository.ContaRepository;
import com.BankSpring.Model.Service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContaController {
    private final ContaRepository contaRepository;
    private final ContaService contaService;

    public ContaController(ContaRepository contaRepository, ContaService contaService){
        this.contaService = contaService;
        this.contaRepository = contaRepository;
    }

    @GetMapping("/contasPoupanca")
    public List<ContaBancaria> buscarPoupanca(){
        return contaRepository.findAll();
    }

    @GetMapping("/contaPoupanca/{id}")
    public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable Long id){
        Optional<ContaBancaria> optionalContaPoupanca = contaRepository.findById(id);

        if(optionalContaPoupanca.isPresent()){
            return ResponseEntity.ok(optionalContaPoupanca.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
GsonBuilderUtils
    @PostMapping("/novaContaPoupanca")
    public ContaBancaria novaPoupanca(@RequestBody ContaBancaria contaPoupanca){
        return contaRepository.save(contaPoupanca);
    }

    @PutMapping("/transferir")
    public ResponseEntity<ContaBancaria> transferir(@RequestParam Long id, double valor){
        ContaBancaria result = contaService.transferir(id, valor);
        if (result!=null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/sacar")
    public ResponseEntity<ContaBancaria> sacar(@RequestParam Long id, double valor){
        ContaBancaria result = contaService.sacar(id, valor);
        if(result!=null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/depositar")
    public ResponseEntity<ContaBancaria> depositar(@RequestParam Long id, double valor){
        ContaBancaria result = contaService.depositar(id, valor);

        if(result!= null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
