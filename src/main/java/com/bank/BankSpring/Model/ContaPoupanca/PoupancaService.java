package com.bank.BankSpring.Model.ContaPoupanca;
import org.springframework.stereotype.Service;

@Service
public class PoupancaService {
    private final PoupancaRepository poupancaRepository;

    public PoupancaService (PoupancaRepository poupancaRepository){
        this.poupancaRepository = poupancaRepository;
    }

    public void inserirContaPoupanca(ContaPoupanca contaPoupanca){
        poupancaRepository.save(contaPoupanca);
    }
}
