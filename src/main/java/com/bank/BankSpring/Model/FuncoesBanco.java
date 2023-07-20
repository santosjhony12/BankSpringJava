package com.bank.BankSpring.Model;

import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class FuncoesBanco{
    private ClienteRepository clienteRepository;
    Cliente cliente = new Cliente();
    Scanner scanner = new Scanner(System.in);
    public static void tracejado(){
        System.out.println("====================================================");
    }
    public int login(){

        System.out.println("Digite seu CPF: ");
        String cpfScanner = scanner.next();

        System.out.println("Digite sua senha: ");
        String senhaScanner = scanner.next();

        Cliente cliente = buscarClientePorCpf(cpfScanner);

        System.out.println(cliente.getName());
        if (senhaScanner.equals(cliente.getSenha())){
            System.out.println("\nAcesso Liberado");
            return 1;
        }else{
            System.out.println("\nAcesso Negado!");
            return 0;
        }
    }
    private Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

}
