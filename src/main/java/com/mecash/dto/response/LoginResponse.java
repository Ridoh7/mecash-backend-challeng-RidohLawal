package com.mecash.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Response payload returned after
 * a successful login attempt.
 *
 * Contains the authenticated user's
 * email address and a status message.
 */
@Data
@Builder
public class LoginResponse {

    private String email;

    private String message;
}