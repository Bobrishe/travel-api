package com.alexki.dataapi.handler;

import com.alexki.dataapi.exception.NullMessageException;
import com.alexki.dataapi.exception.WrongSecretKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(WrongSecretKeyException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handle(WrongSecretKeyException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(NullMessageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(NullMessageException exception) {
        return exception.getMessage();
    }

}
