package com.mecash.dto.request;

import lombok.Data;

/**
 * Request payload for updating
 * a user's profile information.
 *
 * Profile details are optional during
 * registration and can be provided later
 * through the profile update endpoint.
 */
@Data
public class UpdateProfileRequest {

    private String firstName;

    private String lastName;
}