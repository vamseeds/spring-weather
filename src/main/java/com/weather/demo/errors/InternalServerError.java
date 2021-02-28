package com.weather.demo.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String message){
        super(message);
    }
}
