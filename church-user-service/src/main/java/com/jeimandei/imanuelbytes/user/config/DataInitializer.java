package com.jeimandei.imanuelbytes.user.config;

import com.jeimandei.imanuelbytes.common.enums.UserStatus;
import com.jeimandei.imanuelbytes.user.entity.Role;
import com.jeimandei.imanuelbytes.user.entity.User;
import com.jeimandei.imanuelbytes.user.repository.RoleRepository;
import com.jeimandei.imanuelbytes.user.repository.UserRepository;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedUsers(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role superAdmin = ensureRole(roleRepository, "ROLE_SUPER_ADMIN", "Full access to everything");
            ensureRole(roleRepository, "ROLE_ADMIN", "Manage church platform");
            ensureRole(roleRepository, "ROLE_EDITOR", "Manage content");
            ensureRole(roleRepository, "ROLE_MEMBER", "Regular member");
            ensureRole(roleRepository, "ROLE_GUEST", "Guest role");

            if (!userRepository.existsByUsername("superadmin")) {
                User user = new User();
                user.setUsername("superadmin");
                user.setEmail("superadmin@imanuelbytes.local");
                user.setPasswordHash(passwordEncoder.encode("ChangeMe123!"));
                user.setFullName("Super Admin");
                user.setStatus(UserStatus.ACTIVE);
                user.setDeleted(false);
                user.setRoles(Set.of(superAdmin));
                userRepository.save(user);
            }
        };
    }

    private Role ensureRole(RoleRepository roleRepository, String roleName, String description) {
        return roleRepository.findByRoleName(roleName)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setRoleName(roleName);
                    role.setDescription(description);
                    return roleRepository.save(role);
                });
    }
}
