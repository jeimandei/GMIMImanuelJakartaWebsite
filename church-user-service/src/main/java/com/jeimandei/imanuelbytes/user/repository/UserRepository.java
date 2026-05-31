package com.jeimandei.imanuelbytes.user.repository;

import com.jeimandei.imanuelbytes.common.enums.UserStatus;
import com.jeimandei.imanuelbytes.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Page<User> findByDeletedFalse(Pageable pageable);

    Page<User> findByDeletedFalseAndStatus(UserStatus status, Pageable pageable);

    Page<User> findByDeletedFalseAndUsernameContainingIgnoreCaseOrDeletedFalseAndEmailContainingIgnoreCase(
            String username,
            String email,
            Pageable pageable
    );
}
