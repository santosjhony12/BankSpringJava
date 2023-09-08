package com.BankSpring.Model.Service;
import com.BankSpring.Model.DTO.ContaBancaria;
import com.BankSpring.Model.Repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    public ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public ContaBancaria transferir(Long id, double valor) {
        Optional<ContaBancaria> poupancaOptional = contaRepository.findById(id);

        if (poupancaOptional.isPresent()) {
            ContaBancaria contaPoupanca = poupancaOptional.get();
            if (contaPoupanca.getSaldo() >= valor) {
                contaPoupanca.transferir(valor);
                return contaRepository.save(contaPoupanca);
            }
        }
        return null;
    }

    public ContaBancaria sacar(Long id, double valor){
        Optional<ContaBancaria> poupancaOptionalSacar = contaRepository.findById(id);

        if(poupancaOptionalSacar.isPresent()){
            ContaBancaria contaPoupanca = poupancaOptionalSacar.get();
            if(contaPoupanca.getSaldo()>=valor){
                contaPoupanca.sacar(valor);
                return contaRepository.save(contaPoupanca);
            }
        }
        return null;
    }
    public ContaBancaria depositar(Long id, double valor){
        Optional<ContaBancaria> optionalContaPoupancaDepo = contaRepository.findById(id);

        if(optionalContaPoupancaDepo.isPresent()){
            ContaBancaria contaPoupanca = optionalContaPoupancaDepo.get();
            contaPoupanca.depositar(valor);
            return contaRepository.save(contaPoupanca);
        }
        return null;
    }
}
