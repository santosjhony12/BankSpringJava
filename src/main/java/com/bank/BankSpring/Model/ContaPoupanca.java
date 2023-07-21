package com.bank.BankSpring.Model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaPoupanca extends ContaBancaria{
    @Column(name = "TIPO_CONTA")
    private String tipoConta;
}
