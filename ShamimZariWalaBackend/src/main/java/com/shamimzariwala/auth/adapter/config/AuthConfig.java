package com.shamimzariwala.auth.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import com.shamimzariwala.auth.adapter.security.JwtService;
import com.shamimzariwala.auth.application.service.AuthService;

@Component
public class AuthConfig {

    @Bean
    public AuthService authService(JwtService jwtService, AuthenticationManager authManager) {
        return new AuthService(jwtService, authManager);
    }
    
}
