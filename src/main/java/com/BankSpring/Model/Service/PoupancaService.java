package com.BankSpring.Model.Service;
import com.BankSpring.Model.DTO.ContaPoupanca;
import com.BankSpring.Model.Exception.ClienteNotFoundException;
import com.BankSpring.Model.Repository.PoupancaRepository;
import org.springframework.stereotype.Service;

@Service
public class PoupancaService {
    private final PoupancaRepository poupancaRepository;

    public PoupancaService (PoupancaRepository poupancaRepository){
        this.poupancaRepository = poupancaRepository;
    }

    public ContaPoupanca transferir(ContaPoupanca contaPoupanca){
        return poupancaRepository.save(contaPoupanca);
    }
    public ContaPoupanca sacar(ContaPoupanca contaPoupanca){
        return poupancaRepository.save(contaPoupanca);
    }
    public ContaPoupanca depositar(ContaPoupanca contaPoupanca){
        return poupancaRepository.save(contaPoupanca);
    }
}
