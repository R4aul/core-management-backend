package com.business_control_system.web.exception;

import com.business_control_system.domain.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleException(NotFoundException exception){
        Error error = new Error("not-found", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException exception){
        List<Error> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
