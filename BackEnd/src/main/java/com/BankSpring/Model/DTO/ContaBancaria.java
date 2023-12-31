package com.BankSpring.Model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONTA_BANCARIA")
public class ContaBancaria {
    @Id
    @Column(name = "NUMERO_CONTA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroConta;

    @Column(name = "SALDO")
    private double saldo;

    @Column(name = "CPF_CLIENTE")
    private String cpf;

    public void transferir(double valor){
        this.saldo -= valor;
    }
    public void sacar(double valor){
        this.saldo -= valor;
    }
    public void depositar(double valor){
        this.saldo += valor;
    }

}
