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

import java.util.List;
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
			return 1;
		}else{
			tracejado();
			System.out.println("\nAcesso Negado! Usuário e/ou senha invalidos\n");
			tracejado();
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
		double saldoCorrente = 0, saldoPoupanca = 0, chequeEspecial = 0, saldoTotal = 0;
		String resposta = "0";
		String cpf = "";


		/* INICIO DO PROGRAMA */
		tracejado();
		System.out.println("\nBEM VINDO AO SEU BANCO DIGITAL\n");
		System.out.println("O QUE GOSTARIA DE FAZER HOJE?");

		while(!resposta.equals("1") && !resposta.equals("2") && !resposta.equals("3")){
			System.out.println("1 - Cadastro\n2 - Entrar na sua conta\n3 - SAIR");
			resposta = scanner.next();

			if (!resposta.equals("1") && !resposta.equals("2") && !resposta.equals("3")){
				tracejado();
				System.out.println("Resposta Invalida.");
				tracejado();
				System.out.println("\nVamos tentar de novo?");
			}
		}


		tracejado();

		if (resposta.equals("1")){
			System.out.println("\nVOCÊ FEZ UMA ÓTIMA ESCOLHA EM FAZER PARTE DESSE NEGÓCIO!\nVAMOS COMEÇAR O SEU CADASTRO!!");
			scanner.nextLine();
			System.out.println("\nDigite o seu nome: ");
			String nome = scanner.nextLine();
			cliente.setName(nome);

			int control = 0;
			boolean control2;
			while (control == 0){
				System.out.println("\nDigite o seu CPF: ");
				cpf = scanner.next();
				control2 = validarCPF(cpf);

				/*VERIFICA SE JÁ EXISTE CPF CADASTRADO*/
				cliente = clienteService.buscarClientePorCpf(cpf);
				if (cliente != null){
					tracejado();
					System.out.println("ESSE CPF JÁ É CADASTRADO");
					tracejado();
				}else{
					control = 1;
				}
				/*VERIFICA SE O CPF É FALSO*/
				if (control2 == false){
					tracejado();
					System.out.println("CPF INVALIDO");
					tracejado();
					control = 0;
				}

			}


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
			System.out.println("FOI UM PRAZER TE TER AQUI. NOS VEMOS EM BREVE!");
			acesso = 3;
			tracejado();
		}

		while(acesso == 0){
			System.out.println("Digite seu CPF: ");
			cpfScanner = scanner.next();

			System.out.println("Digite sua senha: ");
			String senhaScanner = scanner.next();
			acesso = login(cpfScanner, senhaScanner);
		}

		if (acesso == 1) {
			cliente = clienteService.buscarClientePorCpf(cpfScanner);
			contaPoupanca = poupancaService.buscarDadosPorCpf(cpfScanner);
			contaCorrente = correnteService.findByCpf(cpfScanner);
			tracejado();
			System.out.println("\nBEM VINDO " + cliente.getName() + "\nCPF:" + cliente.getCpf());
			acao = "1";

			while (!acao.equals("1") || !acao.equals("2") || !acao.equals("3") || !acao.equals("4") || !acao.equals("5") || !acao.equals("6")) {

				if (acao.equals("7")){
					break;
				}

				questionarioLogado();
				acao = scanner.next();
				tracejado();

				/*OPÇÃO 1 - VER SALDO*/
				if (acao.equals("1")) {
					if (contaPoupanca != null & contaCorrente != null) {
						System.out.println("SALDO CONTA CORRENTE: R$ " + contaCorrente.getSaldo());
						System.out.println("SALDO CONTA POUPANÇA: R$ " + contaPoupanca.getSaldo());
						System.out.println("VALOR CHEQUE ESPECIAL: R$ " + contaCorrente.getChequeEspecial());

						/*CALCULO SALDO TOTAL*/
						chequeEspecial = contaCorrente.getChequeEspecial();
						saldoPoupanca = contaPoupanca.getSaldo();
						saldoCorrente = contaCorrente.getSaldo();
						saldoTotal = saldoPoupanca + saldoCorrente + chequeEspecial;
						System.out.println("SALDO TOTAL: R$ " + saldoTotal);

					} else if (contaPoupanca != null) {
						System.out.println("SALDO CONTA POUPANÇA: R$ " + contaPoupanca.getSaldo());
					} else if (contaCorrente != null) {
						System.out.println("SALDO CONTA CORRENTE: R$ " + contaCorrente.getSaldo());
						System.out.println("VALOR CHEQUE ESPECIAL: R$ " + contaCorrente.getChequeEspecial());

						/*CALCULO SALDO TOTAL*/
						chequeEspecial = contaCorrente.getChequeEspecial();
						saldoCorrente = contaCorrente.getSaldo();
						saldoTotal = saldoCorrente + chequeEspecial;
						System.out.println("SALDO TOTAL: R$ " + saldoTotal);
					}
					tracejado();
				}

				/*OPÇÃO 2 - TRANSFERENCIA*/
				else if (acao.equals("2")) {

					/*SE TIVER AS DUAS CONTAS HÁ UM QUESTIONAMENTO DE ESCOLHA DE CONTA*/
					if (contaPoupanca != null & contaCorrente != null) {
						System.out.printf("Selecione qual conta deseja realizar a transferência: \n1 - Corrente\n2 - Poupança\n");
						acao = scanner.next();
						System.out.printf("Informe a conta que deseja transferir: ");
						String contaTransferir = scanner.next();
						double valorTransferencia = 0;

						/*CONTA CORRENTE*/
						if (acao.equals("1")) {
							System.out.println("Saldo disponível: R$ " + contaCorrente.getSaldo());
							saldoCorrente = contaCorrente.getSaldo();

							while (valorTransferencia > saldoCorrente || valorTransferencia <= 0) {
								System.out.println("\nInforme o valor da transferência: ");
								valorTransferencia = scanner.nextDouble();

								if (valorTransferencia > saldoCorrente) {
									System.out.println("O valor é maior que o há disponível.");
								} else if (valorTransferencia <= 0) {
									System.out.printf("O valor a ser transferido não pode ser menor ou igual a 0.");
								} else if (valorTransferencia > 0 & valorTransferencia <= saldoCorrente) {
									contaCorrente.transferir(valorTransferencia);
									correnteService.transferir(contaCorrente);
									System.out.printf("\n");
									tracejado();
									System.out.println("\nTransferência realizada com sucesso..");
								}
							}
						}
						/*CONTA POUPANÇA*/
						else if (acao.equals("2")) {
							System.out.printf("SALDO DISPONIVEL: R$ " + contaPoupanca.getSaldo());
							saldoPoupanca = contaPoupanca.getSaldo();
							while (valorTransferencia > saldoPoupanca || valorTransferencia <= 0) {
								System.out.println("\nInforme o valor da transferência: ");
								valorTransferencia = scanner.nextDouble();

								if (valorTransferencia > saldoPoupanca) {
									System.out.println("O valor é maior que o há disponível.");
								} else if (valorTransferencia <= 0) {
									System.out.printf("O valor a ser transferido não pode ser menor ou igual a 0.");
								} else if (valorTransferencia > 0 & valorTransferencia <= saldoPoupanca) {
									System.out.printf("\n");
									contaPoupanca.transferir(valorTransferencia);
									poupancaService.transferir(contaPoupanca);
									tracejado();
									System.out.println("\nTransferência realizada com sucesso..");
								}
							}
						}

					}
					/*APENAS CONTA CORRENTE*/
					else if (contaCorrente != null) {
						System.out.printf("Informe a conta que deseja transferir: ");
						String contaTransferir = scanner.next();

						double valorTransferencia = 0;
						saldoCorrente = contaCorrente.getSaldo();

						while (valorTransferencia > saldoCorrente || valorTransferencia <= 0) {
							System.out.println("\nInforme o valor da transferência: ");
							valorTransferencia = scanner.nextDouble();

							if (valorTransferencia > saldoCorrente) {
								System.out.println("O valor é maior que o há disponível.");
							} else if (valorTransferencia <= 0) {
								System.out.printf("O valor a ser transferido não pode ser menor ou igual a 0.");
							} else if (valorTransferencia > 0 & valorTransferencia <= saldoCorrente) {
								contaCorrente.transferir(valorTransferencia);
								correnteService.transferir(contaCorrente);
								System.out.printf("\n");
								tracejado();
								System.out.println("\nTransferência realizada com sucesso..");
							}
						}
					} else if (contaPoupanca != null) {
						System.out.printf("Informe a conta que deseja transferir: ");
						String contaTransferir = scanner.next();

						double valorTransferencia = 0;
						saldoPoupanca = contaPoupanca.getSaldo();

						while (valorTransferencia > saldoPoupanca || valorTransferencia <= 0) {
							System.out.println("Informe o valor da transferência: ");
							valorTransferencia = scanner.nextDouble();

							if (valorTransferencia > saldoCorrente) {
								System.out.println("O valor é maior do que há disponível.");
							} else if (valorTransferencia <= 0) {
								System.out.printf("O valor a ser transferido não pode ser menor ou igual a 0.");
							} else if (valorTransferencia > 0 & valorTransferencia <= saldoPoupanca) {
								contaPoupanca.transferir(valorTransferencia);
								poupancaService.transferir(contaPoupanca);
								System.out.printf("\n");
								tracejado();
								System.out.println("\nTransferência realizada com sucesso..");
							}
						}

					}

				}
				/*AÇÃO 3 - SACAR*/
				else if (acao.equals("3")) {
					double valorSaque = 0;
					double cheque = 0;

					if (contaPoupanca != null & contaCorrente != null) {
						System.out.printf("Selecione qual conta deseja realizar o saque: \n1 - Corrente\n2 - Poupança\n3 - SAIR\n");
						acao = scanner.next();


						/*CONTA CORRENTE*/
						if (acao.equals("1")) {
							saldoCorrente = contaCorrente.getSaldo();
							cheque = contaCorrente.getChequeEspecial();

							if(cheque > 0){
								while (!acao.equals("1") || !acao.equals("2")){
									tracejado();
									System.out.println("\nO que deseja sacar?\n1 - O valor da conta\n2 - Cheque Especial\n3 - SAIR");
									acao = scanner.next();

									if (acao.equals("3")){
										break;
									}

									if (acao.equals("1")){
										if(contaCorrente.getSaldo()<=0){
											System.out.println("Saldo Indisponivel para Saque.");
										}
										else{
										System.out.println("Saldo disponível: R$ " + contaCorrente.getSaldo());

										while (valorSaque > saldoCorrente || valorSaque <= 0) {
											System.out.println("\nInforme o valor do saque: ");
											valorSaque = scanner.nextDouble();

											if (valorSaque > saldoCorrente) {
												System.out.println("O valor é maior do que há disponível.");
											} else if (valorSaque <= 0) {
												System.out.printf("O valor a ser sacado não pode ser menor ou igual a 0.");
											} else if (valorSaque > 0 & valorSaque <= saldoCorrente) {
												contaCorrente.sacar(valorSaque);
												correnteService.sacar(contaCorrente);
												System.out.printf("\n");
												tracejado();
												System.out.println("\nSaque realizado com sucesso..");
											}
										}
										}
									}
									else if (acao.equals("2")){
										System.out.println("Saldo do Cheque Especial: R$ " + contaCorrente.getChequeEspecial());

										cheque = contaCorrente.getChequeEspecial();

										while (valorSaque > cheque || valorSaque <= 0) {
											System.out.println("\nInforme o valor do saque: ");
											valorSaque = scanner.nextDouble();

											if (valorSaque > cheque) {
												System.out.println("O valor é maior do que há disponível.");
											} else if (valorSaque <= 0) {
												System.out.printf("O valor a ser sacado não pode ser menor ou igual a 0.");
											} else if (valorSaque > 0 & valorSaque <= cheque) {
												contaCorrente.sacarCheque(valorSaque);
												correnteService.sacar(contaCorrente);
												System.out.printf("\n");
												tracejado();
												System.out.println("\nSaque realizado com sucesso..");
											}
										}

									}
								}

							} else {

								if (contaCorrente.getSaldo() <= 0) {
									System.out.println("Saldo Indisponivel para Saque.");
								} else {
									System.out.println("Saldo disponível: R$ " + contaCorrente.getSaldo());

									while (valorSaque > saldoCorrente || valorSaque <= 0) {
										System.out.println("\nInforme o valor do saque: ");
										valorSaque = scanner.nextDouble();

										if (valorSaque > saldoCorrente) {
											System.out.println("O valor é maior do que há disponível.");
										} else if (valorSaque <= 0) {
											System.out.printf("O valor a ser sacado não pode ser menor ou igual a 0.");
										} else if (valorSaque > 0 & valorSaque <= saldoCorrente) {
											contaCorrente.sacar(valorSaque);
											correnteService.sacar(contaCorrente);
											System.out.printf("\n");
											tracejado();
											System.out.println("\nSaque realizado com sucesso..");
										}
									}
								}
							}

						}
						/*CONTA POUPANÇA*/
						else if (acao.equals("2")) {
							if (contaPoupanca.getSaldo()<=0){
								System.out.println("Saldo Insuficiente para Saque.");
							}
							else{
							System.out.printf("SALDO DISPONIVEL: R$ " + contaPoupanca.getSaldo());
							saldoPoupanca = contaPoupanca.getSaldo();
							while (valorSaque > saldoPoupanca || valorSaque <= 0) {
								System.out.println("\nInforme o valor do saque: ");
								valorSaque = scanner.nextDouble();

								if (valorSaque > saldoPoupanca) {
									System.out.println("O valor é maior do que há disponível.");
								} else if (valorSaque <= 0) {
									System.out.printf("O valor a ser sacado não pode ser menor ou igual a 0.");
								} else if (valorSaque > 0 & valorSaque <= saldoPoupanca) {
									System.out.printf("\n");
									contaPoupanca.sacar(valorSaque);
									poupancaService.sacar(contaPoupanca);
									tracejado();
									System.out.println("\nSaque realizado com sucesso..");
								}
							}
							}
						}

					}
					/*APENAS CONTA CORRENTE*/
					else if (contaCorrente != null) {
						saldoCorrente = contaCorrente.getSaldo();

						if (contaCorrente.getChequeEspecial() > 0) {
							while (!acao.equals("1") || !acao.equals("2")) {
								System.out.println("O que deseja sacar?\n 1 - O valor da conta\n2 - Cheque Especial\n");
								acao = scanner.next();

								if (acao.equals("1")) {

									if(saldoCorrente<=0){
										System.out.println("Saldo Insuficiente para saque.");
									}else {

										System.out.println("Saldo disponível: R$ " + contaCorrente.getSaldo());

										while (valorSaque > saldoCorrente || valorSaque <= 0) {
											System.out.println("\nInforme o valor do saque: ");
											valorSaque = scanner.nextDouble();

											if (valorSaque > saldoCorrente) {
												System.out.println("O valor é maior do que há disponível.");
											} else if (valorSaque <= 0) {
												System.out.printf("O valor a ser sacado não pode ser menor ou igual a 0.");
											} else if (valorSaque > 0 & valorSaque <= saldoCorrente) {
												contaCorrente.sacar(valorSaque);
												correnteService.sacar(contaCorrente);
												System.out.printf("\n");
												tracejado();
												System.out.println("\nSaque realizado com sucesso..");
											}
										}
									}
								} else if (acao.equals("2")) {
									System.out.println("Saldo do Cheque Especial: R$ " + contaCorrente.getChequeEspecial());

									cheque = contaCorrente.getChequeEspecial();

									while (valorSaque > cheque || valorSaque <= 0) {
										System.out.println("\nInforme o valor do saque: ");
										valorSaque = scanner.nextDouble();

										if (valorSaque > cheque) {
											System.out.println("O valor é maior do que há disponível.");
										} else if (valorSaque <= 0) {
											System.out.printf("O valor a ser sacado não pode ser menor ou igual a 0.");
										} else if (valorSaque > 0 & valorSaque <= cheque) {
											contaCorrente.sacarCheque(valorSaque);
											correnteService.sacar(contaCorrente);
											System.out.printf("\n");
											tracejado();
											System.out.println("\nSaque realizado com sucesso..");
										}
									}

								}
							}

						}
					}
					else if (contaPoupanca != null) {
						if (contaPoupanca.getSaldo()<=0){
							System.out.println("Saldo Insuficiente para Saque.");
						}
						else{
						System.out.println("Saldo disponível: R$ " + contaPoupanca.getSaldo());

						while (valorSaque > saldoPoupanca || valorSaque <= 0) {
							System.out.println("\nInforme o valor do saque: ");
							valorSaque = scanner.nextDouble();

							if (valorSaque > saldoPoupanca) {
								System.out.println("O valor é maior do que há disponível.");
							} else if (valorSaque <= 0) {
								System.out.printf("O valor a ser sacado não pode ser menor ou igual a 0.");
							} else if (valorSaque > 0 & valorSaque <= saldoPoupanca) {
								contaPoupanca.sacar(valorSaque);
								poupancaService.sacar(contaPoupanca);
								System.out.printf("\n");
								tracejado();
								System.out.println("\nSaque realizado com sucesso..");
							}
						}
						}
					}

				/*OPÇÃO 4 - DEPOSITAR*/
				} else if (acao.equals("4")) {
					String tipoConta = "";

					if (contaPoupanca!= null && contaCorrente!= null){
						while (!tipoConta.equals("1") && !tipoConta.equals("2")){
							System.out.println("Informe em qual conta deseja depositar:\n1 - Conta Corrente\n2 - Conta Poupança");
							tipoConta = scanner.next();
							if (!tipoConta.equals("1") && !tipoConta.equals("2")){
								tracejado();
								System.out.println("OPÇÃO INVALIDA");
								tracejado();
							}
						}
					}

					System.out.println("Informe o valor do depósito: \n");
					double valor = scanner.nextDouble();

					if(contaPoupanca!=null && contaCorrente!=null){

						if (tipoConta.equals("1")){
							try{
								contaCorrente.depositar(valor);
								correnteService.depositar(contaCorrente);
								tracejado();
								System.out.println("DEPÓSITO REALIZADO COM SUCESSO");
								tracejado();
							}catch (Exception e){
								System.out.println("Alguma coisa de errado ocorreu. Entre em contato com o seu admnistrador.");
								e.printStackTrace();
							}
						} else if (tipoConta.equals("2")) {
							try{
								contaPoupanca.depositar(valor);
								poupancaService.depositar(contaPoupanca);
								tracejado();
								System.out.println("DEPÓSITO REALIZADO COM SUCESSO");
								tracejado();
							}catch (Exception e){
								System.out.println("Alguma coisa de errado ocorreu. Entre em contato com o seu admnistrador.");
								e.printStackTrace();
							}

						}
						else{
							tracejado();
							System.out.println("Opção Invalida");
							tracejado();
						}
					} else if (contaPoupanca != null) {
						try{
							contaPoupanca.depositar(valor);
							poupancaService.depositar(contaPoupanca);
							tracejado();
							System.out.println("DEPÓSITO REALIZADO COM SUCESSO");
							tracejado();
						}catch (Exception e){
							System.out.println("Alguma coisa de errado ocorreu. Entre em contato com o seu admnistrador.");
							e.printStackTrace();
						}
					} else if (contaCorrente != null) {
						try{
							contaCorrente.depositar(valor);
							correnteService.depositar(contaCorrente);
							tracejado();
							System.out.println("DEPÓSITO REALIZADO COM SUCESSO");
							tracejado();
						}catch (Exception e){
							System.out.println("Alguma coisa de errado ocorreu. Entre em contato com o seu admnistrador.");
							e.printStackTrace();
						}
					}

				}
				/*AÇÃO 7 - SAIR*/
				else if (acao.equals("7")) {
					System.out.println("FOI BOM TE VER AQUI. NOS VEMOS EM BREVE!!");
					tracejado();
					break;
				}
				/*AÇÃO 5 - ALTERAR SENHA*/
				else if (acao.equals("5")) {
					System.out.println("Informe sua nova senha: ");
					String novaSenha = scanner.next();

					try{
						cliente.setSenha(novaSenha);
						clienteService.atualizarSenha(cliente);
						tracejado();
						System.out.println("Senha Alterada com Sucesso!");
						tracejado();
					}catch (Exception e){
						System.out.println("Alguma coisa de errado aconteceu! Entre em contato com o seu Administrador.");
					}
				}
				/*AÇÃO 6 - EXCLUIR CONTA*/
				else if (acao.equals("6")) {
					while(!acao.equals("1") && !acao.equals("2")){
						System.out.println("Tem certeza que deseja excluir sua conta?\n1 - SIM\n2 - Não");
						acao = scanner.next();

						if (acao.equals("1")){
							cpf = cliente.getCpf();
							try{
								poupancaService.excluirContaPorCpf(cpf);
								acao = "1";
							}catch (Exception e){
								e.printStackTrace();
							}
							try{
								correnteService.excluirClientePorCpf(cpf);
								acao = "1";
							}catch (Exception e){
								e.printStackTrace();
							}
							try{
								clienteService.excluirClientePorCpf(cpf);
								acao = "1";
							}catch (Exception e){
								e.printStackTrace();
							}

							if (acao.equals("1")){
								tracejado();
								System.out.println("ESPERAMOS TE VER EM BREVE!");
								tracejado();
								acao = "7";
								break;
							}
						}
					}
				}
			}
		}
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
		System.out.println("4 - Depositar");
		System.out.println("5 - Alterar Senha");
		System.out.println("6 - Excluir conta");
		System.out.println("7 - SAIR\n");
	}

	public static boolean validarCPF(String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");
		if (cpf.length() != 11) {
			return false;
		}
		if (cpf.matches("(\\d)\\1{10}")) {
			return false;
		}

		int soma = 0;
		int resto;

		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}

		resto = 11 - (soma % 11);
		if (resto == 10 || resto == 11) {
			resto = 0;
		}

		if (resto != Character.getNumericValue(cpf.charAt(9))) {
			return false;
		}

		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}

		resto = 11 - (soma % 11);
		if (resto == 10 || resto == 11) {
			resto = 0;
		}

		if (resto != Character.getNumericValue(cpf.charAt(10))) {
			return false;
		}

		return true;
	}


}
