package dev.Rodrigo.fidentBank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.rodrigo.fidentbank.dto.UsuarioRequestDto;
import dev.rodrigo.fidentbank.model.Conta;
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
        UsuarioRequestDto dto = new UsuarioRequestDto("Rodrigo",LocalDate.of(2006,11,04), "rodrigo@gmail.com", "12345678901", "99999999999", "11115");
        Mockito.when(usuarioRepository.existsByEmail(dto.email())).thenReturn(false);
        Mockito.when(usuarioRepository.existsByCpf(dto.cpf())).thenReturn(false);
        
    
        usuarioService.criarUsuario(dto);
        Mockito.verify(usuarioRepository, Mockito.times(1)).save(Mockito.any(Usuario.class));
    
    
    }

    @Test
    public void deveCriptografarPin() {
    UsuarioRequestDto dto = new UsuarioRequestDto("Rodrigo",LocalDate.of(2006,11,04), "rodrigo@gmail.com", "12345678901", "99999999999", "11115");
    Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
    Mockito.when(usuarioRepository.existsByCpf(Mockito.anyString())).thenReturn(false);

    usuarioService.criarUsuario(dto);

    ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
    Mockito.verify(usuarioRepository).save(captor.capture());
    
    assertNotEquals("12534", captor.getValue().getPin());
    }

    @Test
    public void deveGerarNumeroUnicoComSucesso() {
        UsuarioRequestDto dto = new UsuarioRequestDto("Rodrigo",LocalDate.of(2006,11,04), "rodrigo@gmail.com", "12345678901", "99999999999", "11115");
        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
        Mockito.when(usuarioRepository.existsByCpf(Mockito.anyString())).thenReturn(false);
        Mockito.when(contaRepository.existsByNumeroConta(Mockito.anyString())).thenReturn(false);

        usuarioService.criarUsuario(dto);

        ArgumentCaptor<Conta> captor = ArgumentCaptor.forClass(Conta.class);
        Mockito.verify(contaRepository).save(captor.capture());

        assertEquals(new BigDecimal("1500"), captor.getValue().getSaldo());
        assertNotNull(captor.getValue().getNumeroConta());
    }



}



