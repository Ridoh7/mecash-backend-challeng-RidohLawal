package com.mecash.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Response payload returned after successful user registration.
 *
 * Contains the newly created account details
 * and assigned currency information.
 */
@Data
@Builder
public class SignupResponse {

    private String email;
    private String accountNumber;
    private String currency;
    private String message;
    private String balance;
}