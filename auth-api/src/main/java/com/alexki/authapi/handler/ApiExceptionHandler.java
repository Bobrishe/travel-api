package com.alexki.authapi.handler;

import com.alexki.authapi.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({NullMessageException.class,
            AuthenticationException.class,
            UserExistException.class,
            UserNotFoundException.class,
            NullMessageException.class})
    public ResponseEntity<String> handle(Exception exception) {
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
