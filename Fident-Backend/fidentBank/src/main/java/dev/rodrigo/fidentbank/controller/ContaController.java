package dev.rodrigo.fidentbank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.rodrigo.fidentbank.dto.ContaResponseDto;
import dev.rodrigo.fidentbank.model.Conta;
import dev.rodrigo.fidentbank.service.ContaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @GetMapping("/{numeroConta}")
    public ResponseEntity<ContaResponseDto> buscarContaPorNumero(@PathVariable String numeroConta) {
        Conta conta = contaService.buscarContaPorNumero(numeroConta);
        ContaResponseDto responseDto =  new ContaResponseDto(
                conta.getUsuario().getNome(),
                conta.getSaldo(),
                conta.getNumeroConta()
        );
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping()
    public ResponseEntity<conta> buscarTodasContas() {
        Conta conta = contaService.buscarTodasContas();
        ContaResponseDto responseDto =  new ContaResponseDto(
                conta.getUsuario().getNome(),
    }

}
