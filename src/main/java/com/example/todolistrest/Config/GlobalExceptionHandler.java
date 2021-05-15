package com.example.todolistrest.Config;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFound ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex,WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(),"Validation Failed",ex.getBindingResult().toString());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
