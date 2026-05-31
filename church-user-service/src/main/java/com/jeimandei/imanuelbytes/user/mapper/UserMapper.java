package com.jeimandei.imanuelbytes.user.mapper;

import com.jeimandei.imanuelbytes.user.dto.UserResponse;
import com.jeimandei.imanuelbytes.user.entity.User;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setProfileImageUrl(user.getProfileImageUrl());
        response.setStatus(user.getStatus());
        response.setLastLoginAt(user.getLastLoginAt());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setRoles(user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toSet()));
        return response;
    }
}
