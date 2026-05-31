package com.jeimandei.imanuelbytes.user.service.impl;

import com.jeimandei.imanuelbytes.common.enums.UserStatus;
import com.jeimandei.imanuelbytes.common.exception.ResourceNotFoundException;
import com.jeimandei.imanuelbytes.user.dto.CreateUserRequest;
import com.jeimandei.imanuelbytes.user.dto.UpdateUserRequest;
import com.jeimandei.imanuelbytes.user.dto.UserResponse;
import com.jeimandei.imanuelbytes.user.entity.Role;
import com.jeimandei.imanuelbytes.user.entity.User;
import com.jeimandei.imanuelbytes.user.exception.DuplicateResourceException;
import com.jeimandei.imanuelbytes.user.mapper.UserMapper;
import com.jeimandei.imanuelbytes.user.repository.RoleRepository;
import com.jeimandei.imanuelbytes.user.repository.UserRepository;
import com.jeimandei.imanuelbytes.user.service.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getUsers(String query, UserStatus status, Pageable pageable) {
        if (query != null && !query.isBlank()) {
            return userRepository
                    .findByDeletedFalseAndUsernameContainingIgnoreCaseOrDeletedFalseAndEmailContainingIgnoreCase(query, query, pageable)
                    .map(userMapper::toResponse);
        }
        if (status != null) {
            return userRepository.findByDeletedFalseAndStatus(status, pageable).map(userMapper::toResponse);
        }
        return userRepository.findByDeletedFalse(pageable).map(userMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        return userMapper.toResponse(findActiveUser(id));
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfileImageUrl(request.getProfileImageUrl());
        user.setStatus(UserStatus.ACTIVE);
        user.setDeleted(false);
        user.setRoles(resolveRoles(request.getRoles()));
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = findActiveUser(id);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfileImageUrl(request.getProfileImageUrl());
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = findActiveUser(id);
        user.setDeleted(true);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
    }

    @Override
    public UserResponse updateRoles(Long id, Set<String> roleNames) {
        User user = findActiveUser(id);
        user.setRoles(resolveRoles(roleNames));
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateStatus(Long id, UserStatus status) {
        User user = findActiveUser(id);
        user.setStatus(status);
        return userMapper.toResponse(userRepository.save(user));
    }

    private User findActiveUser(Long id) {
        return userRepository.findById(id)
                .filter(user -> !user.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Set<Role> resolveRoles(Set<String> roleNames) {
        Set<String> requested = roleNames == null || roleNames.isEmpty() ? Set.of("ROLE_MEMBER") : roleNames;
        return requested.stream()
                .map(name -> roleRepository.findByRoleName(name)
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + name)))
                .collect(Collectors.toSet());
    }
}
