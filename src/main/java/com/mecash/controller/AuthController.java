package com.mecash.controller;

import com.mecash.dto.request.LoginRequest;
import com.mecash.dto.request.SignupRequest;
import com.mecash.dto.response.LoginResponse;
import com.mecash.dto.response.SignupResponse;
import com.mecash.service.interfaces.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/signup")
    public SignupResponse signup(
            @Valid @RequestBody SignupRequest request) {

        return authService.signup(request);
    }

    @Operation(summary = "Login user")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request));
    }
}