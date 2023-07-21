package com.bank.BankSpring.Model;

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
@Table
public class ContaBancaria {
    @Id @Column(name = "numeroConta") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroConta;
    @Column(name = "saldo")
    private double saldo;

    public void sacar(double valor){
        this.saldo -= valor;
    }
    public void depositar (double valor){
        this.saldo += valor;
    }
}
