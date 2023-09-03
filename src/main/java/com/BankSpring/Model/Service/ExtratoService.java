package com.BankSpring.Model.Service;

import com.BankSpring.Model.Repository.ExtratoRepository;
import com.BankSpring.Model.DTO.Extrato;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ExtratoService {
    private final ExtratoRepository extratoRepository;

    public ExtratoService(ExtratoRepository extratoRepository) {
        this.extratoRepository = extratoRepository;
    }

    public List<Extrato> buscarExtratoPorCpf(String cpf){
        return extratoRepository.findByCpf(cpf);
    }
    public void inserirExtrato(Extrato extrato){
        extratoRepository.save(extrato);
    }
}
