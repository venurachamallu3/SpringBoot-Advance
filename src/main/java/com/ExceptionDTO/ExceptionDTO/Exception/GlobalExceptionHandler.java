package com.ExceptionDTO.ExceptionDTO.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice

public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> UserNotFoundExceptionHanler(UserNotFoundException ex  , WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false));
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorMessage> handleDuplicateUserException(DuplicateUserException exception, WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }

}
