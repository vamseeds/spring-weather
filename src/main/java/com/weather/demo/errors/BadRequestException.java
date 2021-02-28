package com.weather.demo.errors;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message){
        super(message);
    }
}
