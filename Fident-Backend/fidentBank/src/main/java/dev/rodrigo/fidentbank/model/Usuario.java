package dev.rodrigo.fidentbank.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
 public class Usuario implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UsuarioRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      if (this.role == UsuarioRole.ADMIN) {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
      } else {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
      }
    }

    @Override
    public String getPassword() {
      return this.senha;
    }

    @Override
    public String getUsername() {
      return this.email;
    }
 }
