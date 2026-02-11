package com.swagutv.auth.exception;

public class EmailNotSentException extends RuntimeException{
    public EmailNotSentException(String msg){
        super(msg);
    }
}
