package dev.rodrigo.fidentbank.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.rodrigo.fidentbank.model.Conta;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    
    boolean existsByNumeroConta(String numeroConta);
    java.util.Optional<Conta> findByNumeroConta(String numeroConta);
    
    @Query("Select c.numeroConta FROM Conta c")
    Page<String> findAllNumeroConta(Pageable pageable);

}
