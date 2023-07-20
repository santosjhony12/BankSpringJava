package com.bank.BankSpring.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class FuncoesBanco{
    @Autowired
    private ClienteRepository clienteRepository;
    Cliente cliente = new Cliente();
    Scanner scanner = new Scanner(System.in);
    public static void tracejado(){
        System.out.println("====================================================");
    }

    private Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

}
