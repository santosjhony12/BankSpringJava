package com.BankSpring.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.BankSpring.Model.DTO.Extrato;
import java.util.List;

@Repository
public interface ExtratoRepository extends JpaRepository <Extrato, Long> {
    List<Extrato> findByCpf(String cpf);
}