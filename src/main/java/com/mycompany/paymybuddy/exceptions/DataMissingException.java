package com.mycompany.paymybuddy.exceptions;

public class DataMissingException extends RuntimeException{
    public DataMissingException(String message) {
        super(message);
    }
}
