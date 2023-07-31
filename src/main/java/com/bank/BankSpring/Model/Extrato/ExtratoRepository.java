package com.bank.BankSpring.Model.Extrato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtratoRepository extends JpaRepository <Extrato, Long> {
    List<Extrato> findByCpf(String cpf);
}
