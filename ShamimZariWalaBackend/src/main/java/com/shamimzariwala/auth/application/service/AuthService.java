package com.shamimzariwala.auth.application.service;

import com.shamimzariwala.auth.application.port.input.AuthUseCase;
import com.shamimzariwala.auth.adapter.security.JwtService;
import com.shamimzariwala.auth.domain.AuthToken;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthService implements AuthUseCase {


    private final JwtService jwtService;
    private final AuthenticationManager authManager;


    public AuthService(JwtService jwtService, AuthenticationManager authManager) {

        this.jwtService = jwtService;
        this.authManager = authManager;
  
    }

    @Override
    public AuthToken login(String username, String rawPassword) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, rawPassword));
        String role = auth.getAuthorities().stream()
            .findFirst()
            .map(GrantedAuthority::getAuthority)
            .orElse("USER");

        String token = jwtService.generateToken(auth.getName(),role);


        return new AuthToken(token, 3600L);
    }
}