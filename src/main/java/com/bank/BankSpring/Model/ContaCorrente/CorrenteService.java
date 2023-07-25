package com.bank.BankSpring.Model.ContaCorrente;

import org.springframework.stereotype.Service;

@Service
public class CorrenteService {
    private final CorrenteRepository correnteRepository;

    public CorrenteService(CorrenteRepository correnteRepository) {
        this.correnteRepository = correnteRepository;
    }

    public void inserirContaCorrente(ContaCorrente contaCorrente){
        correnteRepository.save(contaCorrente);
    }
}
