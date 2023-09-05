package com.BankSpring.Model.Controller;

import com.BankSpring.Model.DTO.ContaPoupanca;
import com.BankSpring.Model.Repository.PoupancaRepository;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@NoArgsConstructor
public class PoupancaController {

    ContaPoupanca contaPoupanca;
    PoupancaRepository poupancaRepository;

    @GetMapping("/contasPoupanca")
    public List<ContaPoupanca> buscarPoupanca(){
        return poupancaRepository.findAll();
    }

    @GetMapping("/contaPoupanca/{id}")
    public Optional<ContaPoupanca> buscarContaPorId(@PathVariable Long id){
        Optional<ContaPoupanca> optionalContaPoupanca = poupancaRepository.findById(id);

        if(optionalContaPoupanca.isPresent()){
            return optionalContaPoupanca;
        }else {
            return null;
        }
    }

    @PostMapping("/novaContaPoupanca")
    public ContaPoupanca novaPoupanca(@RequestBody ContaPoupanca contaPoupanca){
        return poupancaRepository.save(contaPoupanca);
    }
}
