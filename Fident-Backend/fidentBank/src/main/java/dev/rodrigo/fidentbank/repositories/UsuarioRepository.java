package dev.rodrigo.fidentbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import dev.rodrigo.fidentbank.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean findByCpf(String cpf);
    
    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    UserDetails findByEmail(String email);

    
}