package com.jeimandei.imanuelbytes.auth.repository;

import com.jeimandei.imanuelbytes.auth.entity.AuthRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {

    Optional<AuthRole> findByRoleName(String roleName);
}
