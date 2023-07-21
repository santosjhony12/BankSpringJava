package com.bank.BankSpring.Model;

import org.springframework.stereotype.Service;

@Service
public class PoupancaService {
    private final PoupancaoRepository poupancaoRepository;

    public PoupancaService(PoupancaoRepository poupancaoRepository){
        this.poupancaoRepository = poupancaoRepository;
    }

    public void inserirContaPoupanca(ContaPoupanca contaPoupanca){
        poupancaoRepository.save(contaPoupanca);
    }
}
