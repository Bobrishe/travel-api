package com.alexki.authapi.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Login or password is incorrect");
    }
}
