package com.project.bloquera.exceptions.handler;

import com.project.bloquera.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handler(ResourceNotFoundException exception) {
        return new ErrorMessage(exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),Instant.now());
    }
}
