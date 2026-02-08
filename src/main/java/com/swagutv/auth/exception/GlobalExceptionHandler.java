package com.swagutv.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiError> handleUserExists(UserAlreadyExistException ex){
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ApiError> handleOtp(InvalidOtpException ex){
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handleCred(InvalidCredentialsException ex){
        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<ApiError> handleNotVerified(EmailNotVerifiedException ex){
        return buildError(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneral(Exception ex){
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Something went Wrong!!");
    }

    private ResponseEntity<ApiError> buildError(HttpStatus status, String message){
        return new ResponseEntity<>(new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
            ), status
        );
    }
}
