package com.example.treasuresim.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for centralizing exception handling across the application.
 * This class provides a mechanism to handle different types of exceptions and convert
 * them into appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // Constants for response map keys
    private static final String ERROR_KEY = "error";
    private static final String MESSAGE_KEY = "message";

    /**
     * Handles InvalidStrategyException by converting it to a BAD_REQUEST response.
     *
     * @param ex The InvalidStrategyException that was thrown
     * @return ResponseEntity containing error details with 400 Bad Request status
     */
    @ExceptionHandler(InvalidStrategyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleInvalidStrategyException(InvalidStrategyException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, "Invalid Strategy");
        response.put(MESSAGE_KEY, ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles IllegalArgumentException by converting it to a BAD_REQUEST response.
     *
     * @param ex The IllegalArgumentException that was thrown
     * @return ResponseEntity containing error details with 400 Bad Request status
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, "Bad Request");
        response.put(MESSAGE_KEY, ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles constraint violation exceptions that occur during validation.
     *
     * @param ex The ConstraintViolationException that was thrown
     * @return ResponseEntity containing validation error message with 400 Bad Request status
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body("Validation failed: " + ex.getMessage());
    }

    /**
     * Handles validation exceptions that occur during method argument validation.
     * Maps field-specific validation errors to their corresponding error messages.
     *
     * @param ex The MethodArgumentNotValidException that was thrown
     * @return ResponseEntity containing map of field errors with 400 Bad Request status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // Extract field errors and their messages
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Fallback handler for all unhandled exceptions.
     * Converts any unhandled exception to an Internal Server Error response.
     *
     * @param ex The Exception that was thrown
     * @return ResponseEntity containing error details with 500 Internal Server Error status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ERROR_KEY, "Internal Server Error");
        response.put(MESSAGE_KEY, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
