package com.jeimandei.imanuelbytes.auth.service.impl;

import com.jeimandei.imanuelbytes.auth.dto.AuthResponse;
import com.jeimandei.imanuelbytes.auth.dto.LoginRequest;
import com.jeimandei.imanuelbytes.auth.dto.RegisterRequest;
import com.jeimandei.imanuelbytes.auth.entity.AuthRole;
import com.jeimandei.imanuelbytes.auth.entity.AuthUser;
import com.jeimandei.imanuelbytes.auth.repository.AuthRoleRepository;
import com.jeimandei.imanuelbytes.auth.repository.AuthUserRepository;
import com.jeimandei.imanuelbytes.auth.service.AuthService;
import com.jeimandei.imanuelbytes.common.enums.UserStatus;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final AuthRoleRepository authRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            AuthUserRepository authUserRepository,
            AuthRoleRepository authRoleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authUserRepository = authUserRepository;
        this.authRoleRepository = authRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (authUserRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (authUserRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        AuthRole defaultRole = authRoleRepository.findByRoleName("ROLE_MEMBER")
                .orElseThrow(() -> new ResourceNotFoundException("Default role not found"));
        AuthUser user = new AuthUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(Set.of(defaultRole));
        AuthUser saved = authUserRepository.save(user);
        return new AuthResponse(saved.getUsername(), saved.getRoles().stream().map(AuthRole::getRoleName).collect(Collectors.toSet()));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        AuthUser user = authUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new ResourceNotFoundException("Invalid credentials");
        }
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        return new AuthResponse(user.getUsername(), user.getRoles().stream().map(AuthRole::getRoleName).collect(Collectors.toSet()));
    }
}
