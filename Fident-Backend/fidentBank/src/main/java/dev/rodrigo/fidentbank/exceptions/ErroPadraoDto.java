package dev.rodrigo.fidentbank.exceptions;

import java.time.LocalDateTime;


public record ErroPadraoDto(
    Integer status,
    String mensagem,
    LocalDateTime timestamp
) {
}