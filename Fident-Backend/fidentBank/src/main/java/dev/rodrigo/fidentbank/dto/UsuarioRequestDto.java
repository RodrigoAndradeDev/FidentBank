package dev.rodrigo.fidentbank.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


public record UsuarioRequestDto (
    @NotBlank(message = "O nome é obrigatório")
    String nome,
    
    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser uma data no passado")
    LocalDate dataNascimento,
    
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    String email,
   
    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11 , message = "O CPF deve ter 11 dígitos")
    String cpf,
    
    @NotBlank(message = "O telefone é obrigatório")
    @Size(min = 11, max = 11 , message = "O telefone deve ter 11 dígitos")
    String telefone,
    
    @NotBlank(message = "O pin é obrigatório")
    @Size(min = 5, max = 5 , message = "O pin deve ter 5 dígitos")
    String pin,

    @NotBlank(message = "A senha é obrigatória")
    String senha
)
{}
