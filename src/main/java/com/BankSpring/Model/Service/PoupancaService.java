package com.BankSpring.Model.Service;
import com.BankSpring.Model.DTO.ContaPoupanca;
import com.BankSpring.Model.Repository.PoupancaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PoupancaService {
    private final PoupancaRepository poupancaRepository;
    public PoupancaService (PoupancaRepository poupancaRepository){
        this.poupancaRepository = poupancaRepository;
    }

    public ContaPoupanca transferir(Long id, double valor) {
        Optional<ContaPoupanca> poupancaOptional = poupancaRepository.findById(id);

        if (poupancaOptional.isPresent()) {
            ContaPoupanca contaPoupanca = poupancaOptional.get();
            if (contaPoupanca.getSaldo() >= valor) {
                contaPoupanca.transferir(valor);
                return poupancaRepository.save(contaPoupanca);
            }
        }
        return null;
    }

    public ContaPoupanca sacar(Long id, double valor){
        Optional<ContaPoupanca> poupancaOptionalSacar = poupancaRepository.findById(id);

        if(poupancaOptionalSacar.isPresent()){
            ContaPoupanca contaPoupanca = poupancaOptionalSacar.get();
            if(contaPoupanca.getSaldo()>=valor){
                contaPoupanca.sacar(valor);
                return poupancaRepository.save(contaPoupanca);
            }
        }
        return null;
    }
    public ContaPoupanca depositar(Long id, double valor){
        Optional<ContaPoupanca> optionalContaPoupancaDepo = poupancaRepository.findById(id);

        if(optionalContaPoupancaDepo.isPresent()){
            ContaPoupanca contaPoupanca = optionalContaPoupancaDepo.get();
            contaPoupanca.depositar(valor);
            return poupancaRepository.save(contaPoupanca);
        }
        return null;
    }
}
