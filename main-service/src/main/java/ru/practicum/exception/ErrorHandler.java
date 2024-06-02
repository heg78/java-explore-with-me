package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(final NotFoundException e) {
        log.error("NOT_FOUND Ошибка: {}", e.getMessage());
        return new ResponseEntity<>(
                Map.of("error", e.getMessage(),
                        "errorMessage", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, UncorrectedParametersException.class, MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class})
    public ResponseEntity<Map<String, String>> handlerIncorrectParametersException(final Exception e) {
        log.error("BAD_REQUEST Ошибка: {}", e.getMessage());
        return new ResponseEntity<>(
                Map.of("error", e.getMessage(),
                        "errorMessage", e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({PSQLException.class, ConflictException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, String>> handlerValidationException(final Exception e) {
        log.error("CONFLICT Ошибка: {}", e.getMessage());
        return new ResponseEntity<>(
                Map.of("error", e.getMessage(),
                        "errorMessage", e.getMessage()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handlerOtherException(final Throwable e) {
        log.error("INTERNAL_SERVER_ERROR Ошибка: {}", e.getMessage());
        return new ResponseEntity<>(
                Map.of("error", e.getMessage(),
                        "errorMessage", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}