package com.mecash.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 * Response payload containing
 * user profile information.
 *
 * Returned after a successful
 * profile update operation.
 */
@Data
@Builder
public class UserProfileResponse {

    private String firstName;
    private String lastName;
    private String email;
}