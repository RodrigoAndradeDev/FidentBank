package dev.rodrigo.fidentbank.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.rodrigo.fidentbank.dto.ContaNumeroResponseDto;
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

    @GetMapping("/numeros")
    public ResponseEntity<Page<ContaNumeroResponseDto>> buscarTodasContas(
       @PageableDefault(size = 10, sort = {"numero"}) Pageable paginacao){
        Page<ContaNumeroResponseDto> pagina = contaService.buscarContaPorNumero(paginacao);
        return ResponseEntity.ok(pagina);
    }

    

}
