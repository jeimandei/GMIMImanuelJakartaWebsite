package com.jeimandei.imanuelbytes.auth.repository;

import com.jeimandei.imanuelbytes.auth.entity.AuthUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
