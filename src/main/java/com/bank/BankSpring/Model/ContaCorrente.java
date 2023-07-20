package com.bank.BankSpring.Model;

public class ContaCorrente extends ContaBancaria {
    private String tipo;
    private double chequeEspecial;
    private double taxa;


    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }
    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
    public double getTaxa() {
        return taxa;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }

    @Override
    public void sacar(double valor){
        this.setSaldo(this.getSaldo()-(valor+ valor*this.taxa));
    }
}
