package com.alexki.authapi.exception;

public class ExpiredJwtToken extends Exception {
    public ExpiredJwtToken(){
        super("JWT token Expired!");
    }
}
