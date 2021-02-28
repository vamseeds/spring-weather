package com.weather.demo.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String INVALID_INPUT = "Invalid input";
    public static final String NOT_FOUND = "Not Found";

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity internalServerError(final InternalServerError e) {
        log.warn("Exception Occurred : {}", e.getMessage());
        return ResponseEntity.status(500).body(buildErrorDetail(INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity badRequestException(final BadRequestException e) {
        log.warn("Exception Occurred : {}", e.getMessage());
        return ResponseEntity.status(400).body(buildErrorDetail(INVALID_INPUT, e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundException(final NotFoundException e) {
        log.warn("Exception Occurred : {}", e.getMessage());
        return ResponseEntity.status(404).body(buildErrorDetail(NOT_FOUND, e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("Exception Occurred : {}", ex.getMessage());
        return ResponseEntity.status(400).body(buildErrorDetail(INVALID_INPUT, ex.getMessage()));
    }

    private ErrorDetail buildErrorDetail(String message, String detail) {
        return ErrorDetail.builder().refId(UUID.randomUUID().toString()).message(message).detail(detail).build();
    }
}
