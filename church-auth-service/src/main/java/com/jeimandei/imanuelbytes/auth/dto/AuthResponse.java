package com.jeimandei.imanuelbytes.auth.dto;

import java.util.Set;

public class AuthResponse {

    private String username;
    private Set<String> roles;

    public AuthResponse(String username, Set<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
