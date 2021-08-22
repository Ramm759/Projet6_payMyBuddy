package com.mycompany.paymybuddy.exceptions;

public class DataNotExistException extends RuntimeException{
    public DataNotExistException(String message) {
        super(message);
    }
}
