package com.mecash.service.interfaces;

import com.mecash.dto.request.LoginRequest;
import com.mecash.dto.request.SignupRequest;
import com.mecash.dto.request.UpdateProfileRequest;
import com.mecash.dto.response.LoginResponse;
import com.mecash.dto.response.SignupResponse;
import com.mecash.dto.response.UserProfileResponse;

public interface AuthService {

    /**
     * Registers a new user and automatically creates an account with an opening balance.
     *
     * @param request signup details
     * @return newly created user account information
     */
    SignupResponse signup(SignupRequest request);

    /**
     * Authenticates a user using email and password.
     *
     * @param request login credentials
     * @return login response
     */
    LoginResponse login(LoginRequest request);

    /**
     * Updates the profile information of an authenticated user.
     *
     * @param email user's email address
     * @param request profile update details
     * @return updated user profile information
     */
    UserProfileResponse updateProfile(
            String email,
            UpdateProfileRequest request);
}
