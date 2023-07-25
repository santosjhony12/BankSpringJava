package com.bank.BankSpring;

import com.bank.BankSpring.Model.Cliente.Cliente;
import com.bank.BankSpring.Model.Cliente.ClienteService;
import com.bank.BankSpring.Model.ContaCorrente.ContaCorrente;
import com.bank.BankSpring.Model.ContaCorrente.CorrenteService;
import com.bank.BankSpring.Model.ContaPoupanca.ContaPoupanca;
import com.bank.BankSpring.Model.ContaPoupanca.PoupancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BankSpringApplication implements CommandLineRunner {
	/*INJEÇÃO DE DEPENDENCIA - É NECESSÁRIA TODA VEZ QUE FOR INSERIR UM SERVIÇO*/
	private final ClienteService clienteService;
	private final PoupancaService poupancaService;
	private final CorrenteService correnteService;


	@Autowired
	public BankSpringApplication(ClienteService clienteService, PoupancaService poupancaService, CorrenteService correnteService){
		this.clienteService = clienteService;
		this.poupancaService = poupancaService;
		this.correnteService = correnteService;
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
		String cpfScanner = null;
		Cliente cliente = new Cliente();
		ContaPoupanca contaPoupanca = new ContaPoupanca();
		ContaCorrente contaCorrente	= new ContaCorrente();
		Scanner scanner = new Scanner(System.in);

		/*DECLARAÇÃO DE VARIAVEIS DE CONTROLE*/
		int acesso = 0;
		String acao;




		/* INICIO DO PROGRAMAR */
		tracejado();
		System.out.println("\nBEM VINDO AO SEU BANCO DIGITAL\n");
		System.out.println("O QUE GOSTARIA DE FAZER HOJE?");
		System.out.println("1 - Cadastro\n2 - Entrar na sua conta\n3 - SAIR");
		String resposta = scanner.next();

		tracejado();

		if (resposta.equals("1")){
			System.out.println("\nVOCÊ FEZ UMA ÓTIMA ESCOLHA EM FAZER PARTE DESSE NEGÓCIO!\nVAMOS COMEÇAR O SEU CADASTRO!!");
			scanner.nextLine();
			System.out.println("\nDigite o seu nome: ");
			String nome = scanner.nextLine();
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
				try{
					contaPoupanca.setTipo("P");
					contaPoupanca.setCpf(cpf);
					contaPoupanca.setSaldo(0);
					poupancaService.inserirContaPoupanca(contaPoupanca);
				}catch (Exception e){
					e.printStackTrace();
				}
			}else if(conta.equals("2")){
				try {
					contaCorrente.setTipo("C");
					contaCorrente.setChequeEspecial(100);
					contaCorrente.setSaldo(0);
					contaCorrente.setCpf(cpf);
					correnteService.inserirContaCorrente(contaCorrente);
					System.out.println("Parabéns! Você ganhou um cheque especial de R$ 100,00");;
				}catch (Exception e){
					e.printStackTrace();
				}
			}else if(conta.equals("3")){
				try{
					try{
						contaPoupanca.setTipo("P");
						contaPoupanca.setCpf(cpf);
						contaPoupanca.setSaldo(0);
						poupancaService.inserirContaPoupanca(contaPoupanca);
					}catch (Exception e){
						e.printStackTrace();
					}
					try {
						contaCorrente.setTipo("C");
						contaCorrente.setChequeEspecial(100);
						contaCorrente.setSaldo(0);
						contaCorrente.setCpf(cpf);
						correnteService.inserirContaCorrente(contaCorrente);
						System.out.println("Parabéns! Você ganhou um cheque especial de R$ 100,00");;
					}catch (Exception e){
						e.printStackTrace();
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}


			System.out.println("\nEstamos registrando...");
			System.out.println("\nCADASTRO REALIZADO COM SUCESSO!!");

			System.out.println("\n\nPara iniciar sessão, digite 1. Para sair, digite 2: ");
			resposta = scanner.next();

			if (resposta.equals("1")){
				System.out.println("Digite seu CPF: ");
				cpfScanner = scanner.next();

				System.out.println("Digite sua senha: ");
				String senhaScanner = scanner.next();
				acesso = login(cpfScanner, senhaScanner);
			}

		} else if (resposta.equals("2")) {
			System.out.println("BEM VINDO DE VOLTA! ESTAMOS FELIZES EM TE RECEBE-LO NOVAMENTE");
			System.out.println("Digite seu CPF: ");
			cpfScanner = scanner.next();

			System.out.println("Digite sua senha: ");
			String senhaScanner = scanner.next();
			acesso = login(cpfScanner, senhaScanner);
		}else{
			System.out.println("ATÉ MAIS");
		}

		tracejado();

		if (acesso == 1){
			cliente = clienteService.buscarClientePorCpf(cpfScanner);
			/*É NECESSÁRIO VERIFICAR QUAIS TIPOS DE CONTA O USUÁRIO TEM PARA DEPOIS EXIBIR AS INFORMAÇÕES COMO SALDO ETC.*/
			contaPoupanca = poupancaService.buscarDadosPorCpf(cpfScanner);
			contaCorrente = correnteService.buscarCorrentePorCpf(cpfScanner);
			System.out.println("\nNome: "+cliente.getName()+"\nCPF:"+cliente.getCpf());
			tracejado();
			questionarioLogado();
			tracejado();
			acao = scanner.next();

			if (acao.equals("1")){
				System.out.println("SALDO: R$ ");
			}
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

	/* FUNÇÕES STATICAS PARA VISUAL*/
	public static void tracejado(){
		System.out.println("====================================================");
	}
	public static void questionarioLogado(){
		System.out.println("\nO que deseja realizar?");
		System.out.println("1 - Consultar Saldo");
		System.out.println("2 - Transfência");
		System.out.println("3 - Sacar");
		System.out.println("4 - Alterar Senha");
		System.out.println("5 - Excluir conta");
		System.out.println("6 - SAIR");
	}

}
