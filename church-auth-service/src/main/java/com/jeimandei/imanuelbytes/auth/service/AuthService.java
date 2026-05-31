package com.jeimandei.imanuelbytes.auth.service;

import com.jeimandei.imanuelbytes.auth.dto.AuthResponse;
import com.jeimandei.imanuelbytes.auth.dto.LoginRequest;
import com.jeimandei.imanuelbytes.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
