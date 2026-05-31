package com.jeimandei.imanuelbytes.auth.config;

import com.jeimandei.imanuelbytes.auth.entity.AuthRole;
import com.jeimandei.imanuelbytes.auth.repository.AuthRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(AuthRoleRepository roleRepository) {
        return args -> {
            ensureRole(roleRepository, "ROLE_SUPER_ADMIN");
            ensureRole(roleRepository, "ROLE_ADMIN");
            ensureRole(roleRepository, "ROLE_EDITOR");
            ensureRole(roleRepository, "ROLE_MEMBER");
            ensureRole(roleRepository, "ROLE_GUEST");
        };
    }

    private void ensureRole(AuthRoleRepository roleRepository, String roleName) {
        roleRepository.findByRoleName(roleName).orElseGet(() -> {
            AuthRole role = new AuthRole();
            role.setRoleName(roleName);
            return roleRepository.save(role);
        });
    }
}
