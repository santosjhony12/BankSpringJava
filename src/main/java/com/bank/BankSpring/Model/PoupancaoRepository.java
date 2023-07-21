package com.bank.BankSpring.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoupancaoRepository extends JpaRepository<ContaPoupanca,Long> {
}
