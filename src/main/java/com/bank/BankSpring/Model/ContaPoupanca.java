package com.bank.BankSpring.Model;

public class ContaPoupanca extends ContaBancaria{
    private String tipoConta;

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getTipoConta() {
        return this.tipoConta;
    }
}
