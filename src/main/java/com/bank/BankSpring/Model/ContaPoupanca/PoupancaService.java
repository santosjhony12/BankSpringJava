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
    public ContaPoupanca buscarDadosPorCpf(String cpf){
        return poupancaRepository.findByCpf(cpf);
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
