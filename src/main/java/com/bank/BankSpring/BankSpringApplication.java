package com.bank.BankSpring;

import com.bank.BankSpring.Model.*;
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

	Cliente cliente = new Cliente();
	public int login(String cpf, String senha){
		cliente = clienteService.buscarClientePorCpf(cpf);
		if (cliente != null && senha.equals(cliente.getSenha())){
			System.out.println("\nAcesso Liberado");
			return 1;
		}else{
			System.out.println("\nAcesso Negado!");
			return 0;
		}
	}

	@Override
	public void run(String... args) throws Exception {
		/*INSTANCIAS*/
		Cliente cliente = new Cliente();
		Scanner scanner = new Scanner(System.in);

		/*VARIAVEIS IMPORTANTES*/
		int acesso = 0;

		tracejado();
		System.out.println("\nBEM VINDO AO SEU BANCO DIGITAL\n");
		System.out.println("O QUE GOSTARIA DE FAZER HOJE?");
		System.out.println("1 - Cadastro\n2 - Entrar na sua conta\n3 - SAIR");
		String resposta = scanner.next();

		tracejado();

		if (resposta.equals("1")){
			System.out.println("\nVOCÊ FEZ UMA ÓTIMA ESCOLHA EM FAZER PARTE DESSE NEGÓCIO!\nVAMOS COMEÇAR O SEU CADASTRO!!");

			System.out.println("\nDigite o seu nome: ");
			String nome = scanner.next();
			cliente.setName(nome);

			System.out.println("\nDigite o seu CPF: ");
			String cpf = scanner.next();
			cliente.setCpf(cpf);

			System.out.println("\nDigite sua senha:");
			String senha = scanner.next();
			cliente.setSenha(senha);
			clienteService.inserirCliente(cliente);

			String conta = "0";
			while(!conta.equals("1") && !conta.equals("2") && !conta.equals("3")){
				System.out.println("\nQual tipo de conta gostaria de ter: ");
				System.out.println("\n1 - POUPANÇA\n2 - CORRENTE\n3 - POUPANÇA E CORRENTE");
				conta = scanner.next();
				if (!conta.equals("1") && !conta.equals("2") && !conta.equals("3")){
					System.out.println("OPÇÃO INVALIDA!");
				}
			}
			if (conta.equals("1")){

			}else if(conta.equals("2")){

			}else if(conta.equals("3")){

			}


			System.out.println("\nEstamos registrando...");
			System.out.println("\nCADASTRO REALIZADO COM SUCESSO!!");

			System.out.println("\n\nPara iniciar sessão, digite 1. Para sair, digite 2: ");
			resposta = scanner.next();

			if (resposta.equals("1")){
				System.out.println("Digite seu CPF: ");
				String cpfScanner = scanner.next();

				System.out.println("Digite sua senha: ");
				String senhaScanner = scanner.next();
				acesso = login(cpfScanner, senhaScanner);
			}

		} else if (resposta.equals("2")) {
			System.out.println("BEM VINDO DE VOLTA! ESTAMOS FELIZES EM TE RECEBE-LO NOVAMENTE");
			System.out.println("Digite seu CPF: ");
			String cpfScanner = scanner.next();

			System.out.println("Digite sua senha: ");
			String senhaScanner = scanner.next();
			acesso = login(cpfScanner, senhaScanner);
		}else{
			System.out.println("ATÉ MAIS");
		}

		if (acesso == 1){
			System.out.println("Vamos trabalhar");
		}else{
			System.out.println("Que triste");
		}





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
	public static void tracejado(){
		System.out.println("====================================================");
	}

}
