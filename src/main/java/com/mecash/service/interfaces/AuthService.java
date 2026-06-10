package com.mecash.service.interfaces;

import com.mecash.dto.request.LoginRequest;
import com.mecash.dto.request.SignupRequest;
import com.mecash.dto.response.LoginResponse;
import com.mecash.dto.response.SignupResponse;

public interface AuthService {

    SignupResponse signup(SignupRequest request);

    LoginResponse login(LoginRequest request);
}