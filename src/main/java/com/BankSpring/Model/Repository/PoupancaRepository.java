package com.BankSpring.Model.Repository;

import com.BankSpring.Model.DTO.ContaPoupanca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoupancaRepository extends JpaRepository <ContaPoupanca,Long> {
}
