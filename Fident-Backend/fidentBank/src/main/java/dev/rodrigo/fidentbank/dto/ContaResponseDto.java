package dev.rodrigo.fidentbank.dto;

import java.math.BigDecimal;

public record ContaResponseDto(
    String nomeTitular,
    BigDecimal saldo,
    String numeroConta
) {
    
}
