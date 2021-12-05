package com.bankproj.bankproj.exceptions;

public class TransactionNotAllowedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TransactionNotAllowedException(String message){
        super(message);
    }
}
