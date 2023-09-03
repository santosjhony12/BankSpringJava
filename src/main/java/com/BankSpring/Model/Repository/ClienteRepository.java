package com.BankSpring.Model.Repository;

import com.BankSpring.Model.DTO.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    Cliente findByCpf(String cpf);
}
