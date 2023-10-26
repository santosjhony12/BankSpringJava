package com.BankSpring.Model.Repository;

import com.BankSpring.Model.DTO.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository <ContaBancaria,Long> {
}
