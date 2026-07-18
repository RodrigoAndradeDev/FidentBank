package dev.rodrigo.fidentbank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.rodrigo.fidentbank.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean findByCpf(String cpf);

    boolean findByEmail(String email);
    
    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    
}