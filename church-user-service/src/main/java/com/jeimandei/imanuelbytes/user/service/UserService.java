package com.jeimandei.imanuelbytes.user.service;

import com.jeimandei.imanuelbytes.common.enums.UserStatus;
import com.jeimandei.imanuelbytes.user.dto.CreateUserRequest;
import com.jeimandei.imanuelbytes.user.dto.UpdateUserRequest;
import com.jeimandei.imanuelbytes.user.dto.UserResponse;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponse> getUsers(String query, UserStatus status, Pageable pageable);

    UserResponse getUserById(Long id);

    UserResponse createUser(CreateUserRequest request);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);

    UserResponse updateRoles(Long id, Set<String> roleNames);

    UserResponse updateStatus(Long id, UserStatus status);
}
