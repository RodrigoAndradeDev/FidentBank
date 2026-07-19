package dev.rodrigo.fidentbank.service;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Service;

import dev.rodrigo.fidentbank.dto.UsuarioRequestDto;
import dev.rodrigo.fidentbank.model.Usuario;
import dev.rodrigo.fidentbank.repositories.ContaRepository;
import dev.rodrigo.fidentbank.repositories.UsuarioRepository;

import java.security.SecureRandom;
@Service
public class UsuarioService {
 
    
    private final UsuarioRepository usuarioRepository;
    private final ContaRepository contaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ContaRepository contaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.contaRepository = contaRepository;
    }

    
    public void criarUsuario(UsuarioRequestDto dto){
        avaliadorCpf(dto.cpf());
        avaliadorEmail(dto.email());
        criptografarPin(dto);
        aplicadorDeDados(dto);
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
    private void aplicadorDeDados(UsuarioRequestDto dto){
        Usuario usuario = Usuario.builder()
        .nome(dto.nome())
        .dataNascimento(dto.dataNascimento())
        .email(dto.email())
        .cpf(dto.cpf())
        .telefone(dto.telefone())
        .pin(dto.pin())
        .build();
        usuarioRepository.save(usuario);
    }
    
    public void criptografarPin(UsuarioRequestDto dto){ 
    BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
    textEncryptor.setPasswordCharArray("senha-de-seguranca".toCharArray());


    String textoCriptografado = textEncryptor.encrypt(dto.pin());

    System.out.println(textoCriptografado); 

    System.out.println(textEncryptor.decrypt(textoCriptografado));
    }
    public void geradorNumeroDeConta  (UsuarioRequestDto dto){
        

    }

    public String gerarNumeroUnico() {
    String numeroGerado;
    boolean jaExiste;

    do {
        // Gera um número aleatório de até 10 dígitos (entre 0 e 4.294.967.295)
        long minimo = 0L;
        long maximo = 9999999999L; // 10 dígitos
        long numero = minimo + (long) (Math.random() * (maximo - minimo + 1));
        numeroGerado = String.valueOf(numero);

        // Aqui você faz a consulta no seu Repository do banco de dados
        jaExiste = contaRepository.numeroExiste(numeroGerado);

    } while (jaExiste); // Se já existir no banco, o loop roda de novo e gera outro

    return numeroGerado;
 }
}
    




