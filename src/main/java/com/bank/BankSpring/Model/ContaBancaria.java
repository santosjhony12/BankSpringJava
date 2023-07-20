package com.bank.BankSpring.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ContaBancaria {
    @Id
    private int id;
    @Column(name = "numeroConta")
    private String numeroConta;
    @Column(name = "saldo")
    private double saldo;

    public void sacar(double valor){
        this.saldo -= valor;
    }
    public void depositar (double valor){
        this.saldo += valor;
    }
}
