package dev.rodrigo.fidentbank.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.rodrigo.fidentbank.dto.UsuarioRequestDto;
import dev.rodrigo.fidentbank.dto.UsuarioResponseDto;
import dev.rodrigo.fidentbank.model.Conta;
import dev.rodrigo.fidentbank.model.Usuario;
import dev.rodrigo.fidentbank.repositories.ContaRepository;
import dev.rodrigo.fidentbank.repositories.UsuarioRepository;
import java.math.BigDecimal;
import jakarta.persistence.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
@Service
public class UsuarioService {
 
    
    private final UsuarioRepository usuarioRepository;
    private final ContaRepository contaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ContaRepository contaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional
    public UsuarioResponseDto criarUsuario(UsuarioRequestDto dto){
        avaliadorCpf(dto.cpf());
        avaliadorEmail(dto.email());
        String numeroConta = gerarNumeroUnico();
        aplicadorDeDados(dto, numeroConta);
        
        return new UsuarioResponseDto("Usuário criado com sucesso!", dto.nome(), numeroConta);
    }


    private void criarContaUsuario(Usuario usuario, String numeroConta){
        Conta conta = new Conta();
        conta.setNumeroConta(numeroConta);
        conta.setUsuario(usuario);
        conta.setSaldo(new BigDecimal("1500"));
        conta.setLimite(new BigDecimal("500"));
        contaRepository.save(conta);
        

    }

    //metodo usado para verificar se o cpf ja existe no banco de dados, caso exista ele retorna uma exceção!
    private void avaliadorCpf(String cpf){
        if(usuarioRepository.existsByCpf(cpf)){
            throw new RuntimeException("CPF já cadastrado");
        }
    }

    //metodo usado para verificar se o email ja existe no banco de dados, caso exista ele retorna uma exceção!
    private void avaliadorEmail(String email){
        if(usuarioRepository.existsByEmail(email)){
            throw new RuntimeException("Email já cadastrado");
        }
    }

    //metodo usado para aplicar os dados do dto no banco de dados utilizando o builder do usuario!
    private void aplicadorDeDados(UsuarioRequestDto dto, String numeroConta){
        String pinCriptografado = criptografarPin(dto.pin());
        Usuario usuario = Usuario.builder()
        .nome(dto.nome())
        .dataNascimento(dto.dataNascimento())
        .email(dto.email())
        .cpf(dto.cpf())
        .telefone(dto.telefone())
        .pin(pinCriptografado)
        .dataConta(LocalDateTime.now())
        .build();
        usuarioRepository.save(usuario);
        criarContaUsuario(usuario, numeroConta);
    }
    
    //metodo usado para criptografar o pin do usuario utilizando o BCryptPasswordEncoder!
    private String criptografarPin(String pin){ 

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pinCriptografado = encoder.encode(pin);
        System.out.println("PIN CRIPTOGRAFADO: " + pinCriptografado);
    
        return pinCriptografado;
    }
    
    //metodo usado para gerar um numero unico para a conta do usuario, caso o numero ja exista ele gera outro numero ate encontrar um numero unico!
    private String gerarNumeroUnico() {
    String numeroGerado;
    boolean jaExiste;
    SecureRandom random = new SecureRandom();
    
    do {
        long numero = random.nextLong(1000000000L, 9999999999L);
        numeroGerado = String.valueOf(numero);

        jaExiste = contaRepository.existsByNumeroConta(numeroGerado);
        System.out.println("Número gerado: " + numeroGerado + ", já existe: " + jaExiste);
    } while (jaExiste);
    return numeroGerado;
 }
}
    




