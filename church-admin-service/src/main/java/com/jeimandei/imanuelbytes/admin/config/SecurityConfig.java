package com.jeimandei.imanuelbytes.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "SUPER_ADMIN", "EDITOR")
                        .anyRequest().authenticated())
                .formLogin(form -> form.permitAll())
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"));
        return http.build();
    }
}
