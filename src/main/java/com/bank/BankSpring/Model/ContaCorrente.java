package com.bank.BankSpring.Model;

import jakarta.persistence.Column;

public class ContaCorrente extends ContaBancaria {
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "chequeEspecial")
    private double chequeEspecial;
}
