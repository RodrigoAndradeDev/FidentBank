package dev.Rodrigo.fidentBank.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
 public class Usuario {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "data_nascimento", nullable = false)
    private int dataNascimento;
    
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(name = "data_conta", nullable = false)
    private Date dataConta;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "pin", nullable = false, length = 5)
    private String pin;
 }



 /*
 @Entity -


 @ID -


 @GeneratedValue -


 @Table -
 

 @Column -

 
 strategy -


 unique -



 name -
 
 

 nullable -


 length -


 */