package com.bank.BankSpring.Model.ContaCorrente;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorrenteService {
    private final CorrenteRepository correnteRepository;

    public CorrenteService(CorrenteRepository correnteRepository) {
        this.correnteRepository = correnteRepository;
    }

    public void inserirContaCorrente(ContaCorrente contaCorrente) {
        correnteRepository.save(contaCorrente);
    }

    public ContaCorrente findByCpf(String cpf) {
        return correnteRepository.findByCpf(cpf);
    }
}
