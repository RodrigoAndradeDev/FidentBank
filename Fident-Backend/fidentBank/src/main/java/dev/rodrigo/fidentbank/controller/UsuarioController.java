package dev.rodrigo.fidentbank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import dev.rodrigo.fidentbank.dto.UsuarioRequestDto;
import dev.rodrigo.fidentbank.dto.UsuarioResponseDto;
import dev.rodrigo.fidentbank.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> CadastrarUsuario(@RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto resultado =usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
    
}
