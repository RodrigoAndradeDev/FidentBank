package dev.rodrigo.fidentbank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
 public class Usuario {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(name = "data_conta", nullable = false)
    private LocalDateTime dataConta;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Column(name = "pin", nullable = false, length = 5)
    private String pin;
 }



 /*
 @Entity -
 Essa anotaçao transforma uma classe java em uma tabela de banco de dados

 @ID -
 O indentificador unico (id) diz que um atributo especifico da sua classe é a chave primeiro no banco de dados.Transforma o seu objeto java em uma linha real na tabela   

 @GeneratedValue -
 Diz ao spring boot criar e preencher o ID de um registro no banco de dados de forma automtica 

 @Table -
 Conecta uma classe a tabela do banco de dados, define esquemas e nomes

 @Column -
mapeia um atributo da classe a outra coluna do banco de dados, alem de definir regras 

 @Getter -
 cria automaticamente os métodos para ler os valores de variáveis privadas
 
 @Setter -
 gera metodoso para atributos da sua classe 
 
 @NoArgsConstructor -
 essa notaçao serve pra economizar tempo e evitar a digitaçao do codigo manualmente 
 
 strategy -
permite escolher diferentes regras 

 unique -
 fazer que algo n repita


 name -
 indentificadores de dados a classe
 

 nullable -
 indica que algo n aponta nada referente 


 length -
 usado pra saber o tomanho de um elemento 

 */