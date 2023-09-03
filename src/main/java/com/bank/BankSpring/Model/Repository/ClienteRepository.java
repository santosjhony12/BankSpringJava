package com.bank.BankSpring.Model.Repository;
import com.bank.BankSpring.Model.DTO.Cliente;
import com.bank.BankSpring.Model.DTO.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    Cliente findByCpf(String cpf);

    @Repository
    interface ExtratoRepository extends JpaRepository <Extrato, Long> {
        List<Extrato> findByCpf(String cpf);
    }
}
