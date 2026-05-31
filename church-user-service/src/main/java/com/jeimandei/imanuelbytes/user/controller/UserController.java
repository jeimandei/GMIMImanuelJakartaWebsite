package com.jeimandei.imanuelbytes.user.controller;

import com.jeimandei.imanuelbytes.common.enums.UserStatus;
import com.jeimandei.imanuelbytes.user.dto.CreateUserRequest;
import com.jeimandei.imanuelbytes.user.dto.UpdateUserRequest;
import com.jeimandei.imanuelbytes.user.dto.UpdateUserRolesRequest;
import com.jeimandei.imanuelbytes.user.dto.UpdateUserStatusRequest;
import com.jeimandei.imanuelbytes.user.dto.UserResponse;
import com.jeimandei.imanuelbytes.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserResponse> getUsers(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) UserStatus status,
            Pageable pageable
    ) {
        return userService.getUsers(query, status, pageable);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}/roles")
    public UserResponse updateRoles(@PathVariable Long id, @Valid @RequestBody UpdateUserRolesRequest request) {
        return userService.updateRoles(id, request.getRoles());
    }

    @PutMapping("/{id}/status")
    public UserResponse updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateUserStatusRequest request) {
        return userService.updateStatus(id, request.getStatus());
    }
}
