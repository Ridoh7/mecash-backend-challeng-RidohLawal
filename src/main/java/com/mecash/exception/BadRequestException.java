package com.mecash.exception;

/**
 * Thrown when a client sends an invalid request.
 *
 * Examples:
 * - Invalid login credentials
 * - Insufficient account balance
 * - Negative transfer amount
 * - Duplicate email registration
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}