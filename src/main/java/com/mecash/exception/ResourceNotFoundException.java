package com.mecash.exception;

/**
 * Thrown when a requested resource cannot be found in the system.
 *
 * Examples:
 * - User not found
 * - Account not found
 * - Transaction not found
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}