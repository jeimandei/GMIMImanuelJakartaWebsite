package com.jeimandei.imanuelbytes.user.repository;

import com.jeimandei.imanuelbytes.user.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String roleName);
}
