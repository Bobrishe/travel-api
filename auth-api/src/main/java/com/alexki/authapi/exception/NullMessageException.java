package com.alexki.authapi.exception;

public class NullMessageException extends Exception {

    public NullMessageException() {
        super("The request doesn't contain a text or text is blank.");
    }


}
