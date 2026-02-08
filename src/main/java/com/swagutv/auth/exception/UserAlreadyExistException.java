package com.swagutv.auth.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
