package com.alexki.dataapi.handler;

import com.alexki.dataapi.exception.NullMessageException;
import com.alexki.dataapi.exception.WrongSecretKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(WrongSecretKeyException.class)
    public ResponseEntity<String> handle(WrongSecretKeyException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NullMessageException.class)
    public ResponseEntity<String> handle(NullMessageException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
