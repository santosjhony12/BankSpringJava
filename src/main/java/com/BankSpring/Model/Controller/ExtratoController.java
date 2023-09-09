package com.BankSpring.Model.Controller;

import com.BankSpring.Model.DTO.Extrato;
import com.BankSpring.Model.Repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExtratoController {
    private final ExtratoRepository extratoRepository;

    @Autowired
    public ExtratoController(ExtratoRepository extratoRepository){
        this.extratoRepository = extratoRepository;
    }

    @GetMapping("/extrato/{cpf}")
    public List<Extrato> getAllExtratoId(@PathVariable String cpf){
            List<Extrato> result = extratoRepository.findByCpf(cpf);

            if(result != null){
                return result;
            }else{
                return null;
            }
    }

    @PostMapping("/novoExtrato")
    public Extrato inserindoExtrato(@RequestBody Extrato extrato){
        return extratoRepository.save(extrato);
    }
}
