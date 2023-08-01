package com.bank.BankSpring.Model.Extrato;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Extrato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_acao")
    private LocalDate dataAcao;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "cpf_cliente")
    private String cpf;
    @Column(name = "tipo_conta")
    private String tipoConta;
}
