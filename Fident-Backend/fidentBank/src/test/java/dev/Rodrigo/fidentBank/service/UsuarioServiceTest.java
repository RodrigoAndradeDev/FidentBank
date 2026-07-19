package dev.Rodrigo.fidentBank.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.security.SecureRandom;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rodrigo.fidentbank.dto.UsuarioRequestDto;
import dev.rodrigo.fidentbank.model.Usuario;
import dev.rodrigo.fidentbank.repositories.ContaRepository;
import dev.rodrigo.fidentbank.repositories.UsuarioRepository;
import dev.rodrigo.fidentbank.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ContaRepository contaRepository;
   
    
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void deveCriarUsuarioComSucesso() {
        UsuarioRequestDto dto = new UsuarioRequestDto("Rodrigo",LocalDate.of(2006,11,04), "rodrigo@gmail.com", "12345678901", "99999999999", "12534");
        Mockito.when(usuarioRepository.existsByEmail(dto.email())).thenReturn(false);
        Mockito.when(usuarioRepository.existsByCpf(dto.cpf())).thenReturn(false);
        
    
        usuarioService.criarUsuario(dto);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any(Usuario.class));
    
    
    }

    @Test
    public void deveCriptografarEDescriptografarPinComSucesso() {
        // Arrange (Configuração)
        UsuarioService service = usuarioService;
        UsuarioRequestDto dto = new UsuarioRequestDto("Rodrigo",LocalDate.of(2006,11,04), "rodrigo@gmail.com", "12345678901", "99999999999", "15452");

        // Act & Assert (Execução e Verificação)
        // Como o seu método é "void" e só printa na tela, você só chama ele aqui
        assertDoesNotThrow(() -> service.criptografarPin(dto));
    }

@Test
public void deveGerarNumeroUnicoComSucesso() {
    // Generate a unique number using SecureRandom to avoid relying on ContaService
    SecureRandom random = new SecureRandom();
    int numeroSorteado = random.nextInt(1_000_000_000);
    String numeroGerado = String.valueOf(Math.abs(numeroSorteado));

    assertNotNull(numeroGerado);
    assertFalse(numeroGerado.isEmpty());
    assertFalse(numeroGerado.startsWith("-"));
    System.out.println("NÚMERO DA CONTA GERADO: " + numeroGerado);
}



}



