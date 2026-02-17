package com.swagutv.auth.exception;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException(
            String msg
    ){
        super(msg);
    }
}
