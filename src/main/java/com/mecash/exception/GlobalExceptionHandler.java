package com.mecash.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Centralized exception handling for the application.
 *
 * Converts application exceptions into
 * consistent HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles business validation errors.
     *
     * Examples:
     * - Invalid credentials
     * - Duplicate email
     * - Insufficient balance
     * - Invalid transfer amount
     */
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequest(
            BadRequestException ex) {

        return Map.of(
                "message",
                ex.getMessage()
        );
    }

    /**
     * Handles request validation failures.
     *
     * Triggered when DTO validation annotations
     * such as @NotBlank or @Email fail.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()));

        return errors;
    }

    /**
     * Handles authorization failures.
     *
     * Returned when an authenticated user attempts
     * to access or modify another user's resources.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(
            AccessDeniedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "message",
                        ex.getMessage()
                ));
    }
}