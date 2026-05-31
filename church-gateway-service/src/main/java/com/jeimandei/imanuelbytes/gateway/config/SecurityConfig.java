package com.jeimandei.imanuelbytes.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/about", "/services", "/sermons", "/events", "/livestream", "/ministries", "/contact", "/prayer-request", "/giving", "/news", "/gallery", "/leadership", "/new-here", "/faq", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll());
        return http.build();
    }
}
