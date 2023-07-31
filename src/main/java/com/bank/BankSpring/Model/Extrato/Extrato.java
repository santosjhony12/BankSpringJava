package com.bank.BankSpring.Model.Extrato;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "EXTRATO")
public class Extrato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "DATA_ACAO")
    private LocalDate dataAcao;
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "CPF")
    private String cpf;
}
