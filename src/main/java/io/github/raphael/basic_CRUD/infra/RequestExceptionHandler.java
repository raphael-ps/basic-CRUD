package io.github.raphael.basic_CRUD.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> treatNotFoundException(){
        return ResponseEntity.badRequest().body("Object Not Found!");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> treatNoSuchElementException(){
        return ResponseEntity.badRequest().body("Product Not Found!");
    }

}
