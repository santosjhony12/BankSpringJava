package com.bank.BankSpring.Model.ContaCorrente;

import com.bank.BankSpring.Model.ContaBancaria.ContaBancaria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONTA_BANCARIA")
public class ContaCorrente extends ContaBancaria {
    @Column(name = "chequeEspecial")
    private double chequeEspecial;

    public void sacarCheque(double cheque){
        this.chequeEspecial -= cheque;
    }
}
