package com.BankSpring.Model.Controller;

import com.BankSpring.Model.DTO.ContaPoupanca;
import com.BankSpring.Model.Repository.PoupancaRepository;
import com.BankSpring.Model.Service.PoupancaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PoupancaController {
    private final PoupancaRepository poupancaRepository;
    private final PoupancaService poupancaService;

    public PoupancaController(PoupancaRepository poupancaRepository, PoupancaService poupancaService){
        this.poupancaService = poupancaService;
        this.poupancaRepository = poupancaRepository;
    }

    @GetMapping("/contasPoupanca")
    public List<ContaPoupanca> buscarPoupanca(){
        return poupancaRepository.findAll();
    }

    @GetMapping("/contaPoupanca/{id}")
    public ResponseEntity<ContaPoupanca> buscarContaPorId(@PathVariable Long id){
        Optional<ContaPoupanca> optionalContaPoupanca = poupancaRepository.findById(id);

        if(optionalContaPoupanca.isPresent()){
            return ResponseEntity.ok(optionalContaPoupanca.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/novaContaPoupanca")
    public ContaPoupanca novaPoupanca(@RequestBody ContaPoupanca contaPoupanca){
        return poupancaRepository.save(contaPoupanca);
    }

    @PutMapping("/transferir")
    public ResponseEntity<ContaPoupanca> transferir(@RequestParam Long id, double valor){
        ContaPoupanca result = poupancaService.transferir(id, valor);
        if (result!=null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/sacar")
    public ResponseEntity<ContaPoupanca> sacar(@RequestParam Long id, double valor){
        ContaPoupanca result = poupancaService.sacar(id, valor);
        if(result!=null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/depositar")
    public ResponseEntity<ContaPoupanca> depositar(@RequestParam Long id, double valor){
        ContaPoupanca result = poupancaService.depositar(id, valor);

        if(result!= null){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
