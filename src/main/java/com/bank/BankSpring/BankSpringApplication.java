package com.bank.BankSpring;

import com.bank.BankSpring.Model.Cliente;
import com.bank.BankSpring.Model.ClienteNotFoundException;
import com.bank.BankSpring.Model.ClienteRepository;
import com.bank.BankSpring.Model.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BankSpringApplication implements CommandLineRunner {
	private final ClienteService clienteService;

	@Autowired
	public BankSpringApplication(ClienteService clienteService){
		this.clienteService = clienteService;
	}
	public static void main(String[] args) {
		SpringApplication.run(BankSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Entre com o seu cpf: ");
		String cpf = scanner.next();
		Cliente cliente = new Cliente();
		/*
		* PARA INSERIR O CLIENTE BASTA CHAMAR A FUNÇÃO INSERIRCLIENTE DO
		* CLIENTESERVICE E PASSAR POR PARAMETRO A CLASSE CLIENTE. OBS.: NÃO PODE ESQUECER DE SETAR OS VALORES DA CLASSE
		* cliente.setName("João");
		* cliente.setCpf("123");
		* cliente.setSenha("123);
		* clienteService.inserirCliente(cliente);
		* */


		/* BUSCANDO CLIENTE POR CPF
		cliente = clienteService.buscarClientePorCpf(cpf);

		if (cliente != null){
			System.out.println("Cliente Encontrado");
			System.out.println("ID: "+cliente.getId());
			System.out.println("Nome: "+cliente.getName());
			System.out.println("CPF: "+cliente.getCpf());
			System.out.println(cliente);
		}else{
			System.out.println("Cliente não encontrado!");
		}*/


		/*UPDATE DE SENHA POR CPF
		cliente = clienteService.buscarClientePorCpf(cpf);

		if(cliente != null){
			System.out.println("CLIENTE ENCONTRADO! ");
			System.out.println("Nova senha: ");
			String senha = scanner.next();
			cliente.setSenha(senha);
			clienteService.atualizarSenha(cliente);

		}else{
			System.out.println("CLIENTE NÃO ENCONTRADO!");
		}
*/

		/*DELETE
		try {
			clienteService.excluirClientePorCpf(cpf);
			System.out.println("Cliente excluído com sucesso!");
		} catch (ClienteNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
		*/
	}
}
