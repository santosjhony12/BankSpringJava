package com.bank.BankSpring.Model.ContaPoupanca;

import com.bank.BankSpring.Model.ContaPoupanca.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoupancaRepository extends JpaRepository <ContaPoupanca,Long> {
}
