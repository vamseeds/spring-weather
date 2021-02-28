package com.weather.demo.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
