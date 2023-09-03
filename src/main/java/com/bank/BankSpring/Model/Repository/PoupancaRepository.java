package com.bank.BankSpring.Model.Repository;

import com.bank.BankSpring.Model.DTO.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoupancaRepository extends JpaRepository <ContaPoupanca,Long> {
    ContaPoupanca findByCpf(String cpf);
}
