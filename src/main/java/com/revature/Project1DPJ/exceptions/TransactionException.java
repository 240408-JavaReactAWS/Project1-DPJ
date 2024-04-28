package com.revature.Project1DPJ.exceptions;

public class TransactionException extends RuntimeException{
    public TransactionException(String err){
        super(err);
    }
}
