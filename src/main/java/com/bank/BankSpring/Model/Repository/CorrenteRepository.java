package com.bank.BankSpring.Model.Repository;

import com.bank.BankSpring.Model.DTO.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrenteRepository extends JpaRepository<ContaCorrente, Long> {
    ContaCorrente findByCpf(String cpfCliente);
}
