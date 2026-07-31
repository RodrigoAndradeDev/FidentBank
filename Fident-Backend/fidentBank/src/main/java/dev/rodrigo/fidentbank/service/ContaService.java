package dev.rodrigo.fidentbank.service;

import org.springframework.stereotype.Service;

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

    public Conta buscarTodasContas() {
        return contaRepository.findAll()

}
