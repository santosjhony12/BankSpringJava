package com.bank.BankSpring.Model.Service;

import com.bank.BankSpring.Model.Exception.ClienteNotFoundException;
import com.bank.BankSpring.Model.DTO.ContaCorrente;
import com.bank.BankSpring.Model.Repository.CorrenteRepository;
import org.springframework.stereotype.Service;

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
