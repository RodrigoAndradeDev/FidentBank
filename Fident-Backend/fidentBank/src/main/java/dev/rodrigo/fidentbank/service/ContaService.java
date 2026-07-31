package dev.rodrigo.fidentbank.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.rodrigo.fidentbank.dto.ContaNumeroResponseDto;
import dev.rodrigo.fidentbank.model.Conta;
import dev.rodrigo.fidentbank.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContaService {
    
    private final ContaRepository contaRepository;
    
    public Conta buscarContaPorNumero(String numeroConta) {
       return contaRepository.findByNumeroConta(numeroConta).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada com o número: " + numeroConta));
    }

    public Page<ContaNumeroResponseDto> buscarContaPorNumero(Pageable paginacao) {
        return contaRepository.findAllNumeroConta(paginacao).map(ContaNumeroResponseDto::new);
    }

}
