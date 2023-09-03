package com.bank.BankSpring.Model.Service;
import com.bank.BankSpring.Model.DTO.ContaPoupanca;
import com.bank.BankSpring.Model.Exception.ClienteNotFoundException;
import com.bank.BankSpring.Model.Repository.PoupancaRepository;
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
    public void excluirContaPorCpf(String cpf) {
        ContaPoupanca contaPoupanca = buscarDadosPorCpf(cpf);
        if (contaPoupanca != null) {
            poupancaRepository.delete(contaPoupanca);
            System.out.println("Conta poupança excluída com sucesso");
        } else {
            throw new ClienteNotFoundException("Não possui conta poupança");
        }
    }
}
