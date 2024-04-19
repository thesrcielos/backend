package c14.NoCountry.Controller;


import c14.NoCountry.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAccessDeniedException(AccessDeniedException ex) {
        // Definimos el tipo de error
        ErrorDTO error = ErrorDTO.builder()
                .code("P - 481") // Cambiar el código de error según sea necesario
                .message("You do not have a access permission.")
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = RuntimeException.class)//Definimos los tipos de excepcion a manejar
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex){
        //Definimos el tipo de error, debemos definir un diccionario de errores
        ErrorDTO error = ErrorDTO.builder().code("P-500").message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
    /*
    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(RequestException ex){
        //Aca nos trae el tipo de error directamente de java
        ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }*/
}
