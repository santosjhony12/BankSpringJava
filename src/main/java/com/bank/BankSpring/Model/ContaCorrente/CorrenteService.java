package com.bank.BankSpring.Model.ContaCorrente;

import com.bank.BankSpring.Model.Cliente.Cliente;
import com.bank.BankSpring.Model.Cliente.ClienteNotFoundException;
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
    public ContaCorrente transferir(ContaCorrente contaCorrente){
        return  correnteRepository.save(contaCorrente);
    }
    public ContaCorrente sacar(ContaCorrente contaCorrente){
        return correnteRepository.save(contaCorrente);
    }
    public ContaCorrente depositar(ContaCorrente contaCorrente){
        return correnteRepository.save(contaCorrente);
    }
    public void excluirClientePorCpf(String cpf) {
        ContaCorrente contaCorrente = findByCpf(cpf);
        if (contaCorrente != null) {
            correnteRepository.delete(contaCorrente);
            System.out.println("Conta Corrente excluída com sucesso");
        } else {
            throw new ClienteNotFoundException("O cliente não possui conta corrente");
        }
    }
}
