package com.alexki.dataapi.exception;

public class WrongSecretKeyException extends Exception {

    public WrongSecretKeyException() {
        super("Access Denied! Wrong Secret key.");
    }
}
