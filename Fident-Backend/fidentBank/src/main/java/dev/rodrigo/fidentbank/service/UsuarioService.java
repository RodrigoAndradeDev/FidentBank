package dev.rodrigo.fidentbank.service;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Service;

import dev.rodrigo.fidentbank.dto.UsuarioRequestDto;
import dev.rodrigo.fidentbank.model.Usuario;
import dev.rodrigo.fidentbank.repositories.ContaRepository;
import dev.rodrigo.fidentbank.repositories.UsuarioRepository;

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
    
}



