package com.alexki.dataapi.exception;

public class NullMessageException extends Exception {

    public NullMessageException() {
        super("The request doesn't contain a 'text' field or text is blank.");
    }
}
