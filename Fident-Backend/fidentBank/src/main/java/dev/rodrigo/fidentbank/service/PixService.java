package dev.rodrigo.fidentbank.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PixService {

    private final UsuarioService usuarioService;

    public BigDecimal enviarPix(BigDecimal valor, String numeroContaOrigem, String numeroContaDestino) {
        
        
        
        return valor; 
    }
    
    
}
