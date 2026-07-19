package dev.rodrigo.fidentbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.rodrigo.fidentbank.model.Conta;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    boolean numeroExiste(String numeroGerado);
    
}
