package ru.practicum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidParameterException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({InvalidParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRequestFailedException(Exception e) {
        return e.getMessage();
    }
}