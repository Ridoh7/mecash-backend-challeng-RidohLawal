package com.mecash.controller;

import com.mecash.dto.request.LoginRequest;
import com.mecash.dto.request.SignupRequest;
import com.mecash.dto.request.UpdateProfileRequest;
import com.mecash.dto.response.LoginResponse;
import com.mecash.dto.response.SignupResponse;
import com.mecash.dto.response.UserProfileResponse;
import com.mecash.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles authentication and user profile operations.
 *
 * Provides endpoints for user registration,
 * authentication, and profile management.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Registers a new user and creates an account with an opening balance.
     *
     * @param request registration details
     * @return newly created user account information
     */
    @Operation(summary = "Register a new user")
    @PostMapping("/signup")
    public SignupResponse signup(
            @Valid @RequestBody SignupRequest request) {

        return authService.signup(request);
    }

    /**
     * Authenticates a user using email and password credentials.
     *
     * @param request login credentials
     * @return authentication result
     */
    @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }

    /**
     * Updates the profile information of the authenticated user.
     *
     * @param email user email address
     * @param request profile details
     * @return updated profile information
     */
    @Operation(summary = "Update user profile")
    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @PathVariable String email,
            @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.ok(authService.updateProfile(email, request));
    }
}