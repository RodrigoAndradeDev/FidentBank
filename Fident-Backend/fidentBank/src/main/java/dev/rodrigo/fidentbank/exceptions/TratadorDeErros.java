package dev.rodrigo.fidentbank.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    /*
    metodo de captação de erros nos quais o dado nao é compativel "dez" em um inteiro,
    devolve uma resposta com o formato do ErroPadraoDto amigavel!
    */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadraoDto> tratarErroDeLeitura(HttpMessageNotReadableException ex) {
        
        ErroPadraoDto erro = new ErroPadraoDto(
                400,
                "Formato de dado inválido. Verifique se as datas e os números estão digitados corretamente.",
                LocalDateTime.now()
        );
        
     
        return ResponseEntity.badRequest().body(erro);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErroValidacao(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }
    

    public record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}