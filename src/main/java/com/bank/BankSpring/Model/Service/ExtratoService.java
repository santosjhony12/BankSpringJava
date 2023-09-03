package com.bank.BankSpring.Model.Service;

import com.bank.BankSpring.Model.DTO.Extrato;
import com.bank.BankSpring.Model.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtratoService {
    private final ClienteRepository.ExtratoRepository extratoRepository;

    public ExtratoService(ClienteRepository.ExtratoRepository extratoRepository) {
        this.extratoRepository = extratoRepository;
    }

    public List<Extrato> buscarExtratoPorCpf(String cpf){
        return extratoRepository.findByCpf(cpf);
    }
    public void inserirExtrato(Extrato extrato){
        extratoRepository.save(extrato);
    }
}
